package com.example.springboot.mybatisplus.controller;

import com.example.springboot.mybatisplus.entity.User;
import com.example.springboot.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public void add(User user) {
        boolean save = userService.save(user);

    }
    @RequestMapping("/SaveOrUpdate")
    public void SaveOrUpdate(User user) {
        boolean save = userService.saveOrUpdate(user);

    }

    @RequestMapping("/delete")
    public boolean delete(Long id) {
        return userService.removeById(id);
    }

    @RequestMapping("/delete01")
    public boolean delete01(Long[] id) {
        return userService.removeByIds(Arrays.asList(id));
    }

    @RequestMapping("/update")
    public boolean  update(User user) {
        return userService.updateById(user);
    }

    @RequestMapping("/get")
    public User get(Integer id) {
        return userService.getById(id);
    }

    @RequestMapping("/count")
    public long Page() {
        return userService.count();
    }
}
