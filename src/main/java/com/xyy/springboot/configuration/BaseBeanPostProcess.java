package com.xyy.springboot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * bean初始化的后置处理器，所有bean在创建之后都会调用
 */
@Component
public class BaseBeanPostProcess implements BeanPostProcessor {
    private Logger log = LoggerFactory.getLogger(BaseBeanPostProcess.class);
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug("初始化" + beanName +"之前执行");
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("初始化" + beanName +"之后执行");
        //设置请求必须是同步的，一个执行完，才执行下一个。没有发现应用场景
//        if("requestMappingHandlerAdapter".equals(beanName)){
//            RequestMappingHandlerAdapter requestMappingHandlerAdapter = (RequestMappingHandlerAdapter) bean;
//            requestMappingHandlerAdapter.setSynchronizeOnSession(true);
//            log.debug("设置" + beanName +"的SynchronizeOnSession为true");
//        }
        return bean;
    }
}
