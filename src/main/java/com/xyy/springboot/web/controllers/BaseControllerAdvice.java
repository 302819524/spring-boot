package com.xyy.springboot.web.controllers;

import com.xyy.springboot.configuration.BaseCustomStringEditor;
import com.xyy.springboot.configuration.BaseMethodLog;
import com.xyy.springboot.configuration.BaseTypeLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyy
 * @date 2018/5/24 15:22
 */
@ControllerAdvice
//@BaseTypeLog
public class BaseControllerAdvice{
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    /**
     * 全局应用,每解析一个参数都会执行这个方法，每次的binder都是新建的，
     * 无论客户端传入的是什么类型的请求参数，最终都要以字节的形式传给服务端。而服务端通过Request的getParameter方法取到的参数也都是字符串形式的结果。
     * WebDataBinder 就是将字符串形式的参数转换成服务端真正需要的类型的转换工具
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //由表单到JavaBean赋值过程中哪一个值不进行赋值
        log.info("@InitBinder...");
        binder.setDisallowedFields("noUse");
        // 向binder中注册自定义的编辑器（调用CustomNumberEditor#setAsText）方法，将字符串转化成特定的类型（Long）。
        binder.registerCustomEditor(Long.class,  new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(String.class, "initBinder", new BaseCustomStringEditor());
    }

    /**
     * 全局应用，会将author:xyy保存到每个请求的model中，请求中使用的model和modelMap是一个对象
     * @return
     */
    @ModelAttribute("author")
    public String setAuthor() {
        return "xyy";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String baseException(Exception e){
        log.error("baseException", e);
        return "fail";
    }

}
