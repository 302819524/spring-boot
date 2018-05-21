package com.xyy.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 事件
 */
//@Component
public class BaseApplicationEvent extends ApplicationEvent {
    private static final Logger log = LoggerFactory.getLogger(BaseApplicationEvent.class);
    //事件的信息
    private String msg;

    public BaseApplicationEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseApplicationEvent{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
