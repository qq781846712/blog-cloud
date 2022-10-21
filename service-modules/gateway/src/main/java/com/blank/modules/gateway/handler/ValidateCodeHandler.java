package com.blank.modules.gateway.handler;

import com.blank.common.core.domain.R;
import com.blank.common.core.exception.CaptchaException;
import com.blank.modules.gateway.service.ValidateCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

/**
 * 验证码获取
 *
 * @author ruoyi
 */
@Component
@RequiredArgsConstructor
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {

    private final ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        R<Map<String, Object>> ajax;
        try {
            ajax = validateCodeService.createCaptcha();
        } catch (CaptchaException | IOException e) {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajax));
    }
}
