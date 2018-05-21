package com.xyy.springboot.configuration;

import com.xyy.springboot.properties.BaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
