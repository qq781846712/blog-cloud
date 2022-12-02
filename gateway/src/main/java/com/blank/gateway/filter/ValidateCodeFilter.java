package com.blank.gateway.filter;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.blank.common.core.utils.JsonUtils;
import com.blank.common.core.utils.StringUtils;
import com.blank.gateway.utils.WebFluxUtils;
import com.blank.gateway.config.properties.CaptchaProperties;
import com.blank.gateway.service.ValidateCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 验证码过滤器
 */
@Component
@RequiredArgsConstructor
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {
    private final static String[] VALIDATE_URL = new String[]{"/auth/login", "/auth/register", "/auth/smsLogin"};

    private final ValidateCodeService validateCodeService;

    private final CaptchaProperties captchaProperties;

    private static final String CODE = "code";

    private static final String UUID = "uuid";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 非登录/注册请求或验证码关闭，不处理
            if (!StringUtils.equalsAnyIgnoreCase(request.getURI().getPath(), VALIDATE_URL) || !captchaProperties.getEnabled()) {
                return chain.filter(exchange);
            }

            try {
                String rspStr = WebFluxUtils.resolveBodyFromCacheRequest(exchange);
                Dict obj = JsonUtils.parseMap(rspStr);
                validateCodeService.checkCaptcha(obj.getStr(CODE), obj.getStr(UUID));
            } catch (Exception e) {
                return WebFluxUtils.webFluxResponseWriter(exchange.getResponse(), e.getMessage());
            }
            return chain.filter(exchange);
        };
    }

}
