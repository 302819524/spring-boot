package com.xyy.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xyy
 * @Description:配合BaseAutoConfigutation使用
 * @date 2018/4/1515:31
 */
//不可以写成baseProperties，会读取不到
@ConfigurationProperties(prefix = "base.properties")
public class BaseProperties {
    /**
     * 名称前缀
     */
    private String namePro;

    public String getNamePro() {
        return namePro;
    }

    public void setNamePro(String namePro) {
        this.namePro = namePro;
    }
}
