package com.example.springboot.activemq.mqtest2.controller;


import com.example.springboot.activemq.mqtest2.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @create 2021-12-07 21:49
 */
@RestController
public class MainController {
    @Autowired
    SenderService senderSrv;

    @RequestMapping("send")
    public String send() throws Exception {


        senderSrv.send("springboot","hello~!");

        return "ok";
    }
}
