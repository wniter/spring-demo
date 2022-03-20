package com.example.springboot.thymeleaf.controller;

import com.example.springboot.thymeleaf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return "/index";
    }
}
