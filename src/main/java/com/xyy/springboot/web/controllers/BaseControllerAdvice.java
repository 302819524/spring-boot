package com.xyy.springboot.web.controllers;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author xuyy
 * @date 2018/5/24 15:22
 */
@ControllerAdvice
public class BaseControllerAdvice {

    /**
     * 全局应用
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //由表单到JavaBean赋值过程中哪一个值不进行赋值
        binder.setDisallowedFields("noUse");
    }

    /**
     * 全局应用，会将author:xyy保存到每个请求的model中，请求中使用的model和modelMap是一个对象
     * @return
     */
    @ModelAttribute("author")
    public String setAuthor() {
        return "xyy";
    }

}
