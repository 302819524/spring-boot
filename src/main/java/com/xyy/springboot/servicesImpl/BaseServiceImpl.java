package com.xyy.springboot.servicesImpl;

import com.xyy.springboot.configuration.BaseMethodLog;
import com.xyy.springboot.configuration.BaseTypeLog;
import com.xyy.springboot.domain.BaseUser;
import com.xyy.springboot.listener.BaseApplicationEvent;
import com.xyy.springboot.repository.BaseUserRepository;
import com.xyy.springboot.services.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.Map;

@Service("baseService")
//自己写的日志注解，整个类的方法都应用
@BaseTypeLog
public class BaseServiceImpl implements BaseService{
    private static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    private BaseUserRepository baseUserRepository;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public void putBaseUser(BaseUser baseUser) {
        baseUserRepository.save(baseUser);
    }

    @Override
    public BaseUser getBaseUser(Long id) {
        BaseUser baseUser = baseUserRepository.getById(id);
        return baseUser;
    }

    @Override
    @BaseMethodLog
    public void aspectLog() {
        int i = 0;
        BaseUser baseUser = new BaseUser();
        baseUser.setAge(18);
        baseUser.setName("xyy");
        //如果类实现了接口会使用jdk的代理方式，否则使用cglib的方式
        //事物的实现原理同aop一样
        //spring的动态代理机制，在执行目标方法的时候，先获取目标方法的代理类，在将目标方法和增强方法转化为方法执行链,
        //在进行执行，其中目标方法的执行使用的是this.XXX这样就是直接调用方法，不会再获取调用方法的代理类，执行方法执行链，所以
        //就造成目标方法内部调用的方法无法应用aop，可以在从容器中获取代理类，在执行代理类的目标方法，当然也可以把动态代理转化为编译时
        //代理（即在编译的时候就把需要切入的方法直接编译到.class文件中）<目前不知道怎么实现[尴尬]>
        //可以实现切面方法
        baseService.aspectLogArgs(baseUser, i);
        //可以实现切面方法
        BaseServiceImpl baseServiceImpl = (BaseServiceImpl)applicationContext.getBean("baseService");
        baseServiceImpl.aspectLogArgs2(baseUser, i);
        //不可以实现切面方法
        aspectLogArgs(baseUser, i);
        //不可以实现切面方法
        aspectLogArgs2(baseUser, i);
        //不可以实现切面方法
        this.aspectLogArgs2(baseUser, i);
    }
    @Override
//    @BaseMethodLog
    public void aspectLogArgs(BaseUser baseUser, int i) {
    }
//    @BaseMethodLog
    public void aspectLogArgs2(BaseUser baseUser, int i) {
        throw new NullPointerException();
    }


}
