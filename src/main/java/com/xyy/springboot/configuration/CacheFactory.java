package com.xyy.springboot.configuration;

import com.google.common.cache.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuyy
 * @date 2018/11/5 19:54
 */
public class CacheFactory {
    private static Logger log = LoggerFactory.getLogger(CacheFactory.class);
    public static final Cache<String, String> CACHE = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .build();
    public static final LoadingCache<String, String> CACHE_LOAD = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    log.debug("key" + key);
                    return key + 1;
                }

                @Override
                public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
                    Map<String, String> map = new HashMap<>();
                    keys.forEach(key -> {
                        log.debug("keyAll" + key);
                        map.put(key, key + "2");
                    });
                    return map;
                }
            });
    public static final LoadingCache<String, String> CACHE_LISTEN = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("key " + notification.getKey() + " removed!");
                }
            })
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    log.debug("key" + key);
                    return key + "-load";
                }

                @Override
                public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
                    Map<String, String> map = new HashMap<>();
                    keys.forEach(key -> {
                        log.debug("keyAll" + key);
                        map.put(key, key + "-loadAll");
                    });
                    return map;
                }
            });
    public static final LoadingCache<String, String> CACHE_LISTEN_ASYNC = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .recordStats()

            .removalListener(RemovalListeners.asynchronous(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("key " + notification.getKey() + " removed!");
                }
            }, new ThreadPoolExecutor(5,5,5,TimeUnit.SECONDS,new LinkedBlockingQueue<>())))
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    log.debug("key" + key);
                    return key + "-load";
                }

                @Override
                public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
                    Map<String, String> map = new HashMap<>();
                    keys.forEach(key -> {
                        log.debug("keyAll" + key);
                        map.put(key, key + "-loadAll");
                    });
                    return map;
                }
            });
}
