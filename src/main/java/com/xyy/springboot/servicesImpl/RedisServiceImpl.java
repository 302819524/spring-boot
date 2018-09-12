package com.xyy.springboot.servicesImpl;

import com.xyy.springboot.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author xuyy
 * @date 2018/8/30 18:30
 */
@Service
public class RedisServiceImpl implements RedisService{
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void redisSet() {
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        operations.set("spring", "test");
        System.out.println(operations.get("spring"));
    }
}
