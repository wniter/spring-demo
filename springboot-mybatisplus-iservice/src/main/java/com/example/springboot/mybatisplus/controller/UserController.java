package com.example.springboot.mybatisplus.controller;

import com.example.springboot.mybatisplus.entity.User;
import com.example.springboot.mybatisplus.mapper.UserMapper;
import com.example.springboot.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public int add(User user) {
        return userMapper.insert(user);
    }

    @RequestMapping("/delete")
    public int delete(User user) {
        return userMapper.deleteById(user);
    }

    @RequestMapping("/delete01")
    public int delete01(Long[] id) {
        return userMapper.deleteBatchIds(Arrays.asList(id));
    }

    @RequestMapping("/update")
    public int update(User user) {
        return userMapper.updateById(user);
    }

    @RequestMapping("/select")
    public User select(Integer id) {
        return userMapper.selectById(id);
    }
}
