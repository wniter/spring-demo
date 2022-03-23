package com.example.springboot.rocketmq.controller;

import com.example.springboot.rocketmq.producer.SpringProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/MQTest")
public class MQTestController {

    @Resource
    private SpringProducer springProducer;

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String message){
        springProducer.sendMessage("TestTopic", message);
        return "消息发送完成";
    }
}
