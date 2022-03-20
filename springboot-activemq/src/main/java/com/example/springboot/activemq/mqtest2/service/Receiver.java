package com.example.springboot.activemq.mqtest2.service;

import org.springframework.jms.annotation.JmsListener;

/**
 * @create 2021-12-07 21:08
 */
public class Receiver {
    @JmsListener(destination = "springboot", containerFactory = "jmsListenerContainerTopic")
    public void rece(String msg) {
        System.out.println("收到消息：" + msg);
    }
}
