package com.xyy.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 * 通过实现接口的方式
 * 也可以使用{@link org.springframework.context.event.EventListener}直接在方法上使用
 */
@Component
public class BaseApplicationListenerByInterface implements ApplicationListener<BaseApplicationEvent>{
    private static final Logger log = LoggerFactory.getLogger(BaseApplicationListenerByInterface.class);

    @Override
    public void onApplicationEvent(BaseApplicationEvent event) {
        log.info("BaseApplicationListeners监听器监听到事件：" + event.getMsg());
    }
}
