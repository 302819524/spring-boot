package com.xyy.springboot.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BaseApplicationEventPublisher {
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 事件发布方法
     * @param msg 事件所需的参数
     */
    public void pushListener(String msg) {
        applicationContext.publishEvent(new BaseApplicationEvent(this, msg));
    }

}