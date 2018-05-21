package com.xyy.springboot.configuration;

import com.xyy.springboot.listener.BaseFilter;
import com.xyy.springboot.listener.BaseServletListener;
import com.xyy.springboot.servlet.BaseHttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * servlet三大组件的配置
 * @author xuyy
 * @date 2018/5/21 19:33
 */
@Configuration
public class BaseServerConfig {
    private static final Logger log = LoggerFactory.getLogger(BaseServerConfig.class);

    /**
     * 注册自己的servlet
     * DispatcherServletAutoConfiguration 中使用此方式注册DispatcherServlet 默认是拦截"/"，"/"不拦截jsp请求，"/*"拦截jsp请求
     * @return
     */
    @Bean
    public ServletRegistrationBean baseHttpServlet(){
        ServletRegistrationBean<BaseHttpServlet> registrationBean = new ServletRegistrationBean<>(new BaseHttpServlet());
        registrationBean.addUrlMappings("/baseHttpServlet");
        return registrationBean;
    }

    /**
     * 注册servlet的监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean baseServletListener(){
        ServletListenerRegistrationBean<BaseServletListener> registrationBean = new ServletListenerRegistrationBean<>(new BaseServletListener());
        return registrationBean;
    }

    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean baseFilter(){
        FilterRegistrationBean<BaseFilter> registrationBean = new FilterRegistrationBean<>(new BaseFilter());
        registrationBean.addUrlPatterns("/baseFilter");
        return registrationBean;
    }
}
