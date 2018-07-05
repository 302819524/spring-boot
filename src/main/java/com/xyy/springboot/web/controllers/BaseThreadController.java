package com.xyy.springboot.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.*;

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

    @RequestMapping("/futureTask")
    @ResponseBody
    private String futureTask() throws ExecutionException, InterruptedException {
        log.info("futureTask...");
        FutureTask<String> stringFutureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
//                Thread.sleep(5000);
                return "BaseCallable...";
            }
        });
        Future<?> submit = executorService.submit(stringFutureTask);
        String result = (String)submit.get();
        log.info("线程执行结果1：" + result);//线程执行结果1：null
        result = stringFutureTask.get();
        log.info("线程执行结果2：" + result);//线程执行结果2：BaseCallable...
        executorService.execute(stringFutureTask);
        result = stringFutureTask.get();
        log.info("线程执行结果3：" + result);//线程执行结果3：BaseCallable...
        return "success:callable";
    }

    @RequestMapping("/asyncRequest")
    @ResponseBody
    private Callable<String> asyncRequest() throws ExecutionException, InterruptedException {
        log.info("asyncRequest...");
        return new Callable<String>() {
            public String call() throws Exception {
                log.info("asyncRequest...#call");
                Thread.sleep(5000);
                return "someView";
            }
        };
    }
}
