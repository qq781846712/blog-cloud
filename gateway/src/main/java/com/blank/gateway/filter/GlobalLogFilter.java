package com.blank.gateway.filter;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.blank.common.core.utils.JsonUtils;
import com.blank.common.core.utils.StringUtils;
import com.blank.gateway.config.properties.CustomGatewayProperties;
import com.blank.gateway.utils.WebFluxUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;

import static com.blank.gateway.utils.WebFluxUtils.getOriginalRequestUrl;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * 全局日志过滤器
 * <p>
 * 用于打印请求执行参数与响应时间等等
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalLogFilter implements GlobalFilter, Ordered {

    private final CustomGatewayProperties customGatewayProperties;

    private static final String START_TIME = "startTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!customGatewayProperties.getRequestLog()) {
            return chain.filter(exchange);
        }
        ServerHttpRequest request = exchange.getRequest();
        String path = WebFluxUtils.getOriginalRequestUrl(exchange);
        String url = request.getMethod().name() + " " + path;

        // 打印请求参数
        if (WebFluxUtils.isJsonRequest(exchange)) {
            String jsonParam = WebFluxUtils.resolveBodyFromCacheRequest(exchange);
            log.debug("[PLUS]开始请求 => URL[{}],参数类型[json],参数:[{}]", url, jsonParam);
        } else {
            MultiValueMap<String, String> parameterMap = request.getQueryParams();
            if (MapUtil.isNotEmpty(parameterMap)) {
                String parameters = JsonUtils.toJsonString(parameterMap);
                log.debug("[PLUS]开始请求 => URL[{}],参数类型[param],参数:[{}]", url, parameters);
            } else {
                log.debug("[PLUS]开始请求 => URL[{}],无参数", url);
            }
        }

        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                long executeTime = (System.currentTimeMillis() - startTime);
                log.debug("[PLUS]结束请求 => URL[{}],耗时:[{}]毫秒", url, executeTime);
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
