package com.example.springboot.actuator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @create 2022-03-20 5:55
 */
@RestController
@RequestMapping()
public class HelloController {
    @RequestMapping("/get")
    public  String get(){
        return "hellowolrd";
    }
}
