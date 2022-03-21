package com.example.springboot.activemq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @create 2022-01-19 0:18
 */
@Service
public class Receiver {
    @JmsListener(destination = "springboot",containerFactory = "jmsListenerContainerTopic")
    public void rece(String msg) {

        System.out.println("收到消息：" + msg);
    }
}
