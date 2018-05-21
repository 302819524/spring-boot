package com.xyy.springboot.properties;

/**
 * @author xyy
 * @Description: 无需配置注入的@component等注解，通过BaseConfiguration类注入到容器中
 * @date 2018/4/1514:06
 */
public class BaseBean {
    private String name="xyy";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
