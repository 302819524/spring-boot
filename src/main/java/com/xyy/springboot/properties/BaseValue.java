package com.xyy.springboot.properties;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @Description: 属性单个注入
 * 1、不支持松散语法，大小写必须一致
 * 2、支持spel语法
 * 3、不支持jsr303数据校验
 * 4、不支持复杂类型
 * @author xyy
 * @date 2018/4/1414:17
 */
@Component
//@Validated
public class BaseValue {
//    @Length(max = 3)  校验不起作用
    @Value("${base.name}")
    private String name;
//    @Value("#(Math.random()*100)")  不可以
    @Value("#{11*2}")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
