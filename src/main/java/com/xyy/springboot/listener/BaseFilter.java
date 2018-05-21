package com.xyy.springboot.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author xuyy
 * @date 2018/5/21 19:55
 */
public class BaseFilter implements Filter{
    private static final Logger log = LoggerFactory.getLogger(BaseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("BaseFilter...init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("BaseFilter...doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("BaseFilter...destroy");
    }
}
