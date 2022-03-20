package com.example.springboot.redis.controller;

import com.example.springboot.redis.redistest.redisTest;
import com.example.springboot.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class TestRedisController {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    redisTest redistest;

    @RequestMapping("/test01")
    public String Test() {
        return  redisUtils.ListRedisDemo();

    }

    @RequestMapping("/test")
    public void Test01() throws Exception {
          redistest.Testredis();

    }
}
