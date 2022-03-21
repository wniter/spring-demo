package com.example.springboot.docker.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @create 2021-12-05 1:23
 */
@RestController
@RequestMapping
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "return Docker";
    }
}
