package com.xyy.springboot.web.controllers;

import com.xyy.springboot.domain.BaseUser;
import com.xyy.springboot.listener.BaseApplicationEventPublisher;
import com.xyy.springboot.model.BaseUserModel;
import com.xyy.springboot.properties.BaseConfigurationProperties;
import com.xyy.springboot.properties.BaseImportResource;
import com.xyy.springboot.properties.BaseValue;
import com.xyy.springboot.services.BaseService;
import javafx.scene.input.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
//将该类中的model中的author属性和类型为double的键值对放到session域中（默认使用的是session，不过也可以重写SessionAttributeStore在设置到RequestMappingHandlerAdapter）
//RedirectAttributes 中的键值对不会被缓存
//注入SessionStatus,调用setComplete();方法，会删除session中经过该类保存到session的键值对
@SessionAttributes(names = {"author"}, types = {Double.class})
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

    @PostMapping("/baseHttpServlet")
    @ResponseBody
    public String baseHttpServlet(){
        log.info("baseHttpServlet...");
        return "success";
    }

    @PostMapping("/baseFilter")
    @ResponseBody
    public String baseFilter(){
        log.info("baseFilter...");
        return "success";
    }

    /**
     * @InitBinder实现精准的对象接受，也可以进行校验，在handler接受参数之前会执行
     * 和 @ModelAttribute 可以一起写到@ControllerAdvice标注的类中，这样所有的controller都会应用
     * 或者写一个父类让需要的controller继承
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //由表单到JavaBean赋值过程中哪一个值不进行赋值
        binder.setDisallowedFields("dou");
    }

    /**
     * @ModelAttribute 用在方法上，会在handler（如@PostMapping("/initBinder")方法）之前执行，将参数设置到model中
     * @return
     */
    @ModelAttribute("test")
    public String setAuthor() {
        return "test";
    }

    @RequestMapping("/initBinder")
//    @ResponseBody
    //貌似基础数据类型后面不能跟 BindingResult result,会默认添加一个空的BindingResult里面0error
    //在调用这个方法前会先使用 ReflectionUtils.makeAccessible 强制将方法变为可调用的，即使是使用private 也是可以被调用
    //@Valid BaseUserModel baseUserModel2后如果不跟BindingResult，会把校验的的异常返回，如果加了，会把校验的异常保存到BindingResult中，并接下去执行代码
    private String initBinder(ArrayList<BaseUserModel> list , Integer[] arrays, @Valid BaseUserModel baseUserModel, BindingResult resultModel, Integer aaa, BindingResult result){
        log.info(baseUserModel.toString());
        return "index";
    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public String exception(Exception e){
//        log.error("RuntimeException", e);
//        return "RuntimeException fail";
//    }
//
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public String responseStatusException(ResponseStatusException e){
        log.error("ResponseStatusException", e);
        return "ResponseStatusException fail";
    }

    @RequestMapping("/runtimeException")
    @ResponseBody
    private String exception(){
//        throw new RuntimeException();
        throw new RuntimeException("RuntimeException");
    }

    @RequestMapping("/responseStatusException")
    @ResponseBody
    private String responseStatusException() throws Exception {
//        throw new RuntimeException();
        try {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,"responseStatusException");
        } catch (ResponseStatusException e){
            try {
                throw new RuntimeException(e);
            } catch (RuntimeException e1){
                throw new Exception(e1);
            }
        }
    }
}

