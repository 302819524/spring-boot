package com.xyy.springboot.configuration;

import com.xyy.springboot.model.BasePropertiesModel;
import com.xyy.springboot.properties.BaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xyy
 * @Description:
 * @date 2018/4/1515:13
 */
@Configuration
//启动指定类
@EnableConfigurationProperties(BaseProperties.class)
public class BaseAutoConfiguration {
    private final BaseProperties properties;

    /**
     * 只有一个有参构造器的情况下，参数的值会从容器中拿
     * @param properties
     */
    public BaseAutoConfiguration(BaseProperties properties) {
        this.properties = properties;
    }

    @Bean
    public BasePropertiesModel basePropertiesModel(){
        BasePropertiesModel model = new BasePropertiesModel();
        model.setName(properties.getNamePro() + "xyy");
        return model;
    }
}
