package com.xyy.springboot.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 多线程controller
 * @author xuyy
 * @date 2018/6/9 16:00
 */
@Controller
public class BaseThreadController {
    private static Logger log = LoggerFactory.getLogger(BaseThreadController.class);
    @Autowired
    private ExecutorService executorService;


    @RequestMapping("/callable")
    @ResponseBody
    private String callable() throws ExecutionException, InterruptedException {
        log.info("callable...");
        Future<String> stringFuture1 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "BaseCallable...";
            }
        });
        //执行以下代码，主线程会阻塞等待线程返回结果
//        String result = stringFuture1.get();
//        log.info("callable线程执行结果：" + result);
        Future<String> stringFuture2 = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                throw new Exception("task3 throw exception!");
            }

        });

        try {
            String result = stringFuture2.get();
            log.info("stringFuture2线程执行结果：" + result);
        } catch (InterruptedException e) {
            // 线程被stringFuture2终止进入，使用stringFuture2.cancel(true)方法终止线程
            log.error(e.toString());
        } catch (ExecutionException e) {
            // 线程出现异常进入
            log.error(e.toString());
        }
        return "success:callable";
    }
}
