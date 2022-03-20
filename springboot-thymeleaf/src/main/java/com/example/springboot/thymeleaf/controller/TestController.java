package com.example.springboot.thymeleaf.controller;

import com.example.springboot.thymeleaf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个地方不能用restcontroller
 */
@Controller
@RequestMapping
public class TestController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model map){
        List<User> users = new ArrayList<>();
        for (int i = 1; i< 10;i++){
            User user = new User();
            user.setAge(i);
            user.setName("name" +":"+i);
            user.setEmail("email"+":"+i);
            users.add(user);
        }

        map.addAttribute("users",users);
        //这个return对应的是html
        return "/index";
    }

    @RequestMapping("/test")
    public String test(Model map){
        User u = new User();

        u.setAge(24);
        u.setName("name" +":");
        u.setEmail("email"+":");

        User u1 = new User();
        u1.setName("nico robin");
        u1.setAge(35);
        u1.setAge(24);
        u1.setName("name" +":");
        u1.setEmail("email"+":");

        User u2 = new User();
        u2.setAge(24);
        u2.setName("name" +":");
        u2.setEmail("email"+":");

        List<User> userList = new ArrayList<>();
        userList.add(u);
        userList.add(u1);
        userList.add(u2);
        map.addAttribute("user",u);
        map.addAttribute("userList",userList);
        return "/test";
    }



}
