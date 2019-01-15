package com.xyy.springboot.web.controllers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xyy.springboot.configuration.CacheFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author xuyy
 * @date 2018/11/5 19:17
 */
@Controller
public class GuavaController {
    private static Logger log = LoggerFactory.getLogger(GuavaController.class);

    @RequestMapping("guava")
    @ResponseBody
    public String redis() throws Exception {
        log.debug(CacheFactory.CACHE_LOAD.getUnchecked("110"));
        log.debug(CacheFactory.CACHE_LOAD.getUnchecked("110"));
        List<String> list = new ArrayList<>();
        list.add("110");
        list.add("120");
        log.debug(CacheFactory.CACHE_LOAD.getAll(list).toString());
        log.debug(CacheFactory.CACHE_LOAD.getAll(list).toString());
        String key = "119";
        log.debug(CacheFactory.CACHE_LOAD.get(key, new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("keyCall" + key);
                return key + 3;
            }
        }));
        return "success";
    }

    /**
     * 当缓存已经超过设定的时间，并不会立即进行删除操作，而是需要在调用获取方法时才会去验证是否超时，如果超时则删除，否则一直存在内存中
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("guavaCacheLoad")
    @ResponseBody
    public String guavaCacheLoad() throws Exception {
        log.debug(CacheFactory.CACHE_LISTEN.getUnchecked("111"));
        TimeUnit.SECONDS.sleep(6);
        log.debug("等待6秒");
        log.debug(CacheFactory.CACHE_LISTEN.getUnchecked("110"));
        TimeUnit.SECONDS.sleep(6);
        log.debug("等待6秒");
        CacheFactory.CACHE_LISTEN.getUnchecked("111");
        TimeUnit.SECONDS.sleep(2);
        log.debug("等待2秒");
        CacheFactory.CACHE_LISTEN.getUnchecked("111");
        return "success";
    }

    @RequestMapping("guavaCache")
    @ResponseBody
    public String guavaCache() throws Exception {
        CacheFactory.CACHE_LISTEN.put("111", "111");
        log.debug(CacheFactory.CACHE_LISTEN.get("111", () -> null));
        TimeUnit.SECONDS.sleep(1);
        log.debug("等待1秒");
        log.debug(CacheFactory.CACHE_LISTEN.getIfPresent("111"));
        log.debug(CacheFactory.CACHE_LISTEN.size() + "");
        CacheFactory.CACHE_LISTEN.cleanUp();
        log.debug(CacheFactory.CACHE_LISTEN.getIfPresent("111"));
        log.debug(CacheFactory.CACHE_LISTEN.size() + "");
        log.debug("等待1秒");
        CacheFactory.CACHE_LISTEN.invalidate("111");
        return "success";
    }
}