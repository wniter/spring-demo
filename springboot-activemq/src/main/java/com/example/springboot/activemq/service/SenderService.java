package com.example.springboot.activemq.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.ArrayList;

/**
 * @create 2022-01-19 0:17
 */
@Service
public class SenderService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destination, String s) throws Exception{
        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();

        Connection connection =connectionFactory.createConnection();
        connection.start();
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("xxoo");
                textMessage.setStringProperty("hehe", "enen");
                return textMessage;
            }
        });


    }
    public void send2(String destination, String msg) {


        ArrayList<String> list = new ArrayList<>();

        list.add("malaoshi");
        list.add("lain");
        list.add("zhou");
        jmsMessagingTemplate.convertAndSend(destination, list);
    }
    public void send3(String destination, String msg) {


        ArrayList<String> list = new ArrayList<>();

        list.add("malaoshi");
        list.add("lain");
        list.add("zhou");
        jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(destination), list);
    }
}
