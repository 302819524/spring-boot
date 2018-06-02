package com.xyy.springboot.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @see com.xyy.springboot.web.controllers.BaseRequestBodyAdvice
 * 看注释
 * 处理的是使用@ResponseBody注解的方法
 * @author xuyy
 * @date 2018/6/2 18:40
 */
@ControllerAdvice
public class BaseResponseBodyAdvice implements ResponseBodyAdvice<String>{
    private static final Logger log = LoggerFactory.getLogger(BaseResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        log.debug("ResponseBodyAdvice.supports");
        return true;
    }

    @Nullable
    @Override
    public String beforeBodyWrite(@Nullable String body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.debug("ResponseBodyAdvice.beforeBodyWrite");
        return "ResponseBodyAdvice:" + body;
    }
}
