package com.xyy.springboot.properties;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

/**
 * @Description:使用xml配置bean，Spring不推荐
 * @author xyy
 * @date 2018/4/1414:46
 */
@Component
@ImportResource(locations = {"classpath:base.xml"})
public class BaseImportResource {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
