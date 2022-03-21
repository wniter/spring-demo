package com.example.springboot.activemq.controller;


import com.example.springboot.activemq.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @create 2022-01-19 0:16
 */
@RestController
public class MainController {
    @Autowired
    SenderService senderSrv;

    @RequestMapping("send")
    public String send() {


        try {
            senderSrv.send("springboot","hello~!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok";
    }
}
