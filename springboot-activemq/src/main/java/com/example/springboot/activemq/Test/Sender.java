package com.example.springboot.activemq.Test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送
 *
 * @create 2022-01-17 23:45
 */

public class Sender {
    public static void main(String[] args) throws Exception {
        //1.获取连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://localhost:61616"
        );
        //2.获取一个向ActiveMq的连接
        Connection connection = connectionFactory.createConnection();
        //3.获取Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.找目的地，获取destination
        Queue queue = session.createQueue("uesr");
        //5 1.消息创建者
        MessageProducer producer = session.createProducer(queue);
        //	producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // consumer -> 消费者
        // producer -> 创建者
        //5 2.创建消息
        for (int i = 0; i < 1000; i++) {
            TextMessage textMessage = session.createTextMessage("hi: " + i);
            //5 3.向目的地写入消息
            if (i % 4 == 0) {
                // 设置消息的优先级
                // 对producer 整体设置
                //	producer.setPriority(9);
                //	producer.send(textMessage,DeliveryMode.PERSISTENT,9,1000 * 100);
                textMessage.setJMSPriority(9);
            }
        }

        //6.关闭连接
        connection.close();
        System.out.println("-------------------exit-------------------------------");
    }
}
