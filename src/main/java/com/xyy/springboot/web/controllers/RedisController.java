package com.xyy.springboot.web.controllers;

import com.xyy.springboot.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuyy
 * @date 2018/8/30 18:30
 */
@Controller
public class RedisController {
    @Autowired
    public RedisService redisService;

    @RequestMapping("redis")
    @ResponseBody
    public String redis(){
        redisService.redisSet();
        return "";
    }
}
