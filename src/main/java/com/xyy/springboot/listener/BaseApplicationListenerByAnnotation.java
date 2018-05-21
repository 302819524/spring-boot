package com.xyy.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 使用注解形式监听事件
 * @author xuyy
 * @date 2018/5/16 19:52
 */
@Component
public class BaseApplicationListenerByAnnotation {
    private static final Logger log = LoggerFactory.getLogger(BaseApplicationListenerByAnnotation.class);

    @EventListener(classes = BaseApplicationEvent.class)
    public void listener1(BaseApplicationEvent event){
        log.debug("@EventListener的listener1监听到事件：" + event);
    }

    @EventListener(classes = BaseApplicationEvent.class)
    public void listener2(BaseApplicationEvent event){
        log.debug("@EventListener的listener2监听到事件：" + event);
    }

    @EventListener(classes = ApplicationStartedEvent.class)
    public void listener3(ApplicationStartedEvent event){
        log.debug("容器启动完成：" + event);
    }
}
