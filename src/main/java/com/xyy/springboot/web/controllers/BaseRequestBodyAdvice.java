package com.xyy.springboot.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#initControllerAdviceCache
 * 一定要使用@ControllerAdvice（在处理器适配器（handlerAdapter）加载RequestBodyAdvice接口的先决条件是有这个注解）
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#getDefaultArgumentResolvers
 * 要想调用RequestBodyAdvice接口的方法，要使用@RequestBody注解标注，因为值解析器有一定的顺序，不使用这个注解的化，可能前面的解析器就解析参数了
 * 就不会调用到这个接口方法
 *
 * RequestBodyAdvice 和 ResponseBodyAdvice 不能在一个类中同时实现
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#initControllerAdviceCache
 * 会将RequestBodyAdvice、ResponseBodyAdvice一起放到requestResponseBodyAdvice数组中
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyAdviceChain#RequestResponseBodyAdviceChain(List)
 * 在初始化时，会根据beanType进行分离，如果在一个类中同时实现，则两个advice都会放到requestBodyAdvice中，则responseBodyAdvice方法就不会实现
 * @author xuyy
 * @date 2018/6/2 18:39
 */
@ControllerAdvice
public class BaseRequestBodyAdvice implements RequestBodyAdvice{
    private static final Logger log = LoggerFactory.getLogger(BaseRequestBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.debug("RequestBodyAdvice.supports");
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        log.debug("RequestBodyAdvice.beforeBodyRead");
        return null;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.debug("RequestBodyAdvice.afterBodyRead");
        return null;
    }

    @Nullable
    @Override
    public Object handleEmptyBody(@Nullable Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.debug("RequestBodyAdvice.handleEmptyBody");
        return null;
    }
}
