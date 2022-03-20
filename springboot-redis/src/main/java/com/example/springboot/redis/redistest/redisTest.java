package com.example.springboot.redis.redistest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class redisTest {


    @Autowired
    RedisTemplate redisTemplate;


    @Autowired
    ObjectMapper objectMapper;

    public void Testredis() throws Exception {

    }
}
