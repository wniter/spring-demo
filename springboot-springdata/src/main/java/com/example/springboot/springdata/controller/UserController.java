package com.example.springboot.springdata.controller;

import com.example.springboot.springdata.entity.User;
import com.example.springboot.springdata.repository.UserRepository;
import com.example.springboot.springdata.service.UserService;
import com.example.springboot.springdata.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @ResponseBody
    @RequestMapping("/list")
    public Result<List<User>> findAllUser() {
        List<User> users = userService.findAllUser();
        return new Result<>(users);

    }
    @ResponseBody
    @RequestMapping("/list/{id}")
    public Result<User> findAllUser(Long id) {
        User user = userService.findUserById(id);
        return new Result<>(user);
    }
}
