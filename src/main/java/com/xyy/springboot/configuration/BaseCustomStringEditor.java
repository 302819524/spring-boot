package com.xyy.springboot.configuration;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @InitBinder注解注册的解析器
 * @author xuyy
 * @date 2018/6/6 19:34
 */
public class BaseCustomStringEditor extends PropertyEditorSupport {

    /**
     * 前台传入的字符串类型的数据，转换成后台接口需要的类型
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase());
    }

    @Override
    public String getAsText() {
        return "BaseCustomStringEditor#getAsText 目前不知道怎么样";
    }
}
