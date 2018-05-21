package com.xyy.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author xuyy
 * @date 2018/5/21 19:43
 */
public class BaseServletListener implements ServletContextListener{
    private static final Logger log = LoggerFactory.getLogger(BaseServletListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("BaseServletListener...web应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("contextDestroyed...web应用关闭");
    }
}
