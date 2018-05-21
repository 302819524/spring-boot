package com.xyy.springboot.properties;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * @Description: 批量注入
 * 1、支持松散语法：base.age=24 base.Name=xyy 首字母大写可以导入
 * 2、支持jsr303数据校验
 * @NotNull 注解元素必须是非空
 *
 *  @Null 注解元素必须是空
 *
 *  @Digits 验证数字构成是否合法
 *
 *  @Future 验证是否在当前系统时间之后
 *
 *  @Past 验证是否在当前系统时间之前
 *
 *  @Max 验证值是否小于等于最大指定整数值
 *
 *  @Min 验证值是否大于等于最小指定整数值
 *
 *  @Pattern 验证字符串是否匹配指定的正则表达式
 *
 *  @Size 验证元素大小是否在指定范围内
 *
 *  @DecimalMax 验证值是否小于等于最大指定小数值
 *
 *  @DecimalMin 验证值是否大于等于最小指定小数值
 *
 *  @AssertTrue 被注释的元素必须为true
 *
 *  @AssertFalse 被注释的元素必须为false
 *
 * Hibernate validator 在JSR303的基础上对校验注解进行了扩展，扩展注解如下：
 *
 *  @Email 被注释的元素必须是电子邮箱地址
 *
 *  @Length 被注释的字符串的大小必须在指定的范围内
 *
 *  @NotEmpty 被注释的字符串的必须非空
 *
 *  @Range 被注释的元素必须在合适的范围内
 * 3、支持复杂类型封装，map list 对象
 * @author xyy
 * @date 2018/4/14 13:49
 */
//指定加载某个配置文件，不加注解则从application.properties中获取
//测试发现如果application.properties文件也配置了base的参数，则最终加载的是app李cation中的值
@PropertySource(value = {"classpath:base.properties"})
@Component
//必须是容器的组件才可以使用这个注解，所以要使用@component使该类注入到容器
//每个属性都要有
@ConfigurationProperties(prefix = "base")
@Validated
public class BaseConfigurationProperties {
    @Length(min = 2)
    private String name;
    private Integer age;
    private List<String> list;
    private Map<String,String> map;

    private Double random;

    public Double getRandom() {
        return random;
    }

    public void setRandom(Double random) {
        this.random = random;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "BaseConfigurationProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", list=" + list +
                ", map=" + map +
                ", random=" + random +
                '}';
    }
}
