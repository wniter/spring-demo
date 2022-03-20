package com.example.springboot.redis.service;

import com.example.springboot.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @create 2022-03-20 21:05
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService{
    public StudentServiceImpl() {

    }

    @Autowired
    private RedisUtils redisUtils;
    public String hasValue(String key) {

        return String.valueOf(redisUtils.haskey(key));

    }

}
