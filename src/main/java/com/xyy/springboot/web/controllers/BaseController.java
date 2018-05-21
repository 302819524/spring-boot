package com.xyy.springboot.web.controllers;

import com.xyy.springboot.domain.BaseUser;
import com.xyy.springboot.listener.BaseApplicationEventPublisher;
import com.xyy.springboot.properties.BaseConfigurationProperties;
import com.xyy.springboot.properties.BaseImportResource;
import com.xyy.springboot.properties.BaseValue;
import com.xyy.springboot.services.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class BaseController {
    private static Logger log = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private BaseConfigurationProperties baseConfigurationProperties;
    @Autowired
    private BaseValue baseValue;
    @Autowired
    private BaseImportResource baseImportResource;
    @Autowired
    private BaseService baseService;
    @Autowired
    private BaseApplicationEventPublisher baseApplicationEventPublisher;
    @Autowired
    private ConfigurableEnvironment environment;

    @PostMapping("/baseConfigurationProperties")
    @ResponseBody
    public String baseConfigurationProperties(){
        log.info("进入BaseConfigurationProperties");
        log.info("list" + baseConfigurationProperties.getList().toString());
        log.info("map" + baseConfigurationProperties.getMap().toString());
        return "base" + baseConfigurationProperties.getName();
    }

    @PostMapping("/baseValue")
    @ResponseBody
    public String baseValue(){
        log.info("进入baseValue");
        return "base" + baseValue.getName() + baseValue.getAge();
    }

    @PostMapping("/baseImportResource")
    @ResponseBody
    public String baseImportResource(){
        log.info("进入baseImportResource");
        return "base" + baseImportResource.getName();
    }
    @PostMapping("/logger")
    @ResponseBody
    public String logger(){
        //spring-boot默认打印的日志级别是info。可以通过
        log.trace("trance...");
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
        return "success";
    }
    @GetMapping("/getBaseUser")
    @ResponseBody
    public String getBaseUser(@RequestParam Long id){
        BaseUser baseUser = baseService.getBaseUser(id);
        return "";
    }

    /**
     * 如果使用@requestBody标注BaseUser，则需要指定媒体类型为application/json
     * @param baseUser
     * @return
     */
    @PutMapping("/putBaseUser")
    @ResponseBody
    public String putBaseUser(BaseUser baseUser){
        baseService.putBaseUser(baseUser);
        return "";
    }

    @PostMapping("/aspect")
    @ResponseBody
    public String aspect(){
        baseService.aspectLog();
        return "success";
    }

    @PostMapping("/publishEvent")
    @ResponseBody
    public String publishEvent(){
        baseApplicationEventPublisher.pushListener("发布事件");
        return "success";
    }

    @PostMapping("/environment")
    @ResponseBody
    public String environment(){
        int i = 0;
        for (String key : environment.getSystemEnvironment().keySet()){
            String value = environment.getSystemEnvironment().get(key).toString();
            if (i == 0){
                System.out.println("SystemEnvironment:");
                i++;
            }
            System.out.println(key + " --> " + value);
        }
        i = 0;
        for (String key : environment.getSystemProperties().keySet()){
            String value = environment.getSystemProperties().get(key).toString();
            if (i == 0){
                System.out.println("SystemProperties:");
                i++;
            }
            System.out.println(key + " --> " + value);
        }
        return "success";
    }
}
