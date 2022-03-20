package com.example.springboot.activemq.mqtest2.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.ArrayList;

/**
 * @create 2021-12-07 21:11
 */
@Service
public class SenderService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destination, String msg) throws Exception {
        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("xxoo");
                textMessage.setStringProperty("hehe", "haha");
                return textMessage;
            }
        });
    }

    public void send2(String destination, String msg) throws Exception {
        ArrayList<String> list = new ArrayList<>();

        list.add("1111");
        list.add("2222");
        list.add("3333");
        jmsMessagingTemplate.convertAndSend(destination,list);
    }
    public void send3(String destination, String msg) throws Exception {
        ArrayList<String> list = new ArrayList<>();

        list.add("1111");
        list.add("2222");
        list.add("3333");
        jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(destination),list);
    }
}
