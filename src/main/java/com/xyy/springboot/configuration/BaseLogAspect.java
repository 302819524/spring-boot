package com.xyy.springboot.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * springboot自动开启aop
 */
@Aspect
@Component
public class BaseLogAspect {
    private static final Logger log = LoggerFactory.getLogger(BaseLogAspect.class);

    /**
     *
     *  多个组合  且（&&）、或（||）、非（！）来组合切入点表达式。
     *     例：@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)
     *  execution：用于匹配方法执行的连接点；
     *      *：匹配任何数量字符；
            ..：匹配任何数量字符的重复，如在类型模式中匹配任何数量子包；而在方法参数模式中匹配任何数量参数。
            +：匹配指定类型的子类型；仅能作为后缀放在类型模式后边。
                 java.lang.String    匹配String类型；
                 java.*.String       匹配java包下的任何“一级子包”下的String类型；
                 如匹配java.lang.String，但不匹配java.lang.ss.String
                 java..*            匹配java包及任何子包下的任何类型;
                 如匹配java.lang.String、java.lang.annotation.Annotation
                 java.lang.*ing      匹配任何java.lang包下的以ing结尾的类型；
                 java.lang.Number+  匹配java.lang包下的任何Number的自类型；
                 如匹配java.lang.Integer，也匹配java.math.BigInteger
        within：用于匹配指定类型内的方法执行；
        this：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配；
        target：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；
        args：用于匹配当前执行的方法传入的参数为指定类型的执行方法；
        @within：用于匹配所有持有指定注解类型内的方法；
        @target：用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；
        @args：用于匹配当前执行的方法传入的参数持有指定注解的执行；
        @annotation：用于匹配当前执行方法持有指定注解的方法；
        bean：Spring AOP扩展的，AspectJ没有对于指示符，用于匹配特定名称的Bean对象的执行方法；
        reference pointcut：表示引用其他命名切入点，只有@ApectJ风格支持，Schema风格不支持。
     */
//    只能注释在方法上
    @Pointcut("@annotation(com.xyy.springboot.configuration.BaseMethodLog)")
//    注释在类上，使方法都应用
//    @Pointcut("@within(com.xyy.springboot.configuration.BaseTypeLog)")
    public void pointcut(){}

    @Before("pointcut()")
    public void logStart(JoinPoint joinPoint){
        // 方法名称
        String methodName = joinPoint.getSignature().getName();
        // 方法参数
        Object[] args = joinPoint.getArgs();
        List<Object> list = Arrays.asList(args);
        log.debug(methodName + "方法运行前，参数" + list.toString());
    }

    //如果是别的类调用这个切点，需要写全类名
    //无论目标方法是否出现异常都会执行
    @After("com.xyy.springboot.configuration.BaseLogAspect.pointcut()")
    public void logEnd(JoinPoint joinPoint){
        // 方法名称
        String methodName = joinPoint.getSignature().getName();
        log.debug(methodName + "方法运行后");
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result){
        // 方法名称
        String methodName = joinPoint.getSignature().getName();
        if (null != result)
            log.debug(methodName + "方法运行结束，结果" + result.toString());
        else
            log.debug(methodName + "方法运行结束");
    }

    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public void logReturn(JoinPoint joinPoint, Exception exception){
        // 方法名称
        String methodName = joinPoint.getSignature().getName();
        log.debug(methodName + "方法运行异常", exception);
    }

}
