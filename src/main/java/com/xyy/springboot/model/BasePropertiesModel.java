package com.xyy.springboot.model;

/**
 * @author xyy
 * @Description:通过Springboot的自动配置生成
 * @date 2018/4/1515:46
 */
public class BasePropertiesModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BasePropertiesModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
