package com.example.springboot.mybatisplus;

import com.example.springboot.mybatisplus.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {


    @Autowired
    UserService userService;

}
