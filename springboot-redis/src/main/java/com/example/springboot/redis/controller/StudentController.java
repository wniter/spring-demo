package com.example.springboot.redis.controller;

import com.example.springboot.redis.entity.Student;
import com.example.springboot.redis.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @create 2022-03-20 21:00
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IRedisService iRedisService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @RequestMapping("/test")
    public void testRedis(){
//      iRedisService.set("aaa","BBB");
        redisTemplate.opsForValue().set("student",new Student(1,"123",111));
        Student student = (Student)redisTemplate.opsForValue().get("student");
        System.out.println(student);
    }

}
