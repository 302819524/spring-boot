package com.xyy.springboot.configuration;

import com.xyy.springboot.properties.BaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xyy
 * @Description:
 * @date 2018/4/1514:04
 */
//标注这是一个配置文件
@Configuration
public class BaseConfiguration {
    private static Logger log = LoggerFactory.getLogger(BaseConfiguration.class);

    @Bean
    public BaseBean baseBean(){
        log.info("BaseBean注入容器。。。");
        return new BaseBean();
    }

    /**
     * callable多线程接口使用的服务，类似于继承runnable接口的方法需要thread调用一样
     * 用法见baseThreadController
     * @return
     */
    @Bean
    public ExecutorService executorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        return executorService;
    }
}
