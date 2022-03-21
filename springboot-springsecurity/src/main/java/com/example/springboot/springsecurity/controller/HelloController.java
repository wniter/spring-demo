package com.example.springboot.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 默认：
 username : 用户名 - user
 password : 密码
 _csrf : CSRF保护方面的内容，暂时先不展开解释
 */
@Controller
public class HelloController {


    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello ,spring security!";
    }
}
