//package com.xyy.springboot;
//
//import com.xyy.springboot.model.BasePropertiesModel;
//import com.xyy.springboot.properties.BaseBean;
//import com.xyy.springboot.properties.BaseConfigurationProperties;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.BeansException;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ApplicationTests implements ApplicationContextAware {
//
//    private ApplicationContext ApplicationContext;
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.ApplicationContext = applicationContext;
//    }
//
//    @Test
//    public void contextLoads() {
//        if ( ApplicationContext.containsBean("baseBean")){
//            System.out.println(((BaseBean)ApplicationContext.getBean("baseBean")).getName());
//        }else {
//            System.out.println("bad");
//        }
//    }
//
//    @Test
//    public void test2() {
//        if ( ApplicationContext.containsBean("baseConfigurationProperties")){
//            System.out.println(((BaseConfigurationProperties)ApplicationContext.getBean("baseConfigurationProperties")).toString());
//        }else {
//            System.out.println("bad");
//        }
//    }
//    @Test
//    public void test3() {
//        if ( ApplicationContext.containsBean("basePropertiesModel")){
//            System.out.println(((BasePropertiesModel)ApplicationContext.getBean("basePropertiesModel")).toString());
//        }else {
//            System.out.println("bad");
//        }
//    }
//}
