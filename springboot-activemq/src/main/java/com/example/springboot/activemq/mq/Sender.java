package com.example.springboot.activemq.mq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送
 *
 * @create 2021-12-06 16:11
 */
public class Sender {
    public static void main(String[] args) throws Exception {
        //1.获取连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        //2.获取一个向ActiveMQ的连接

        Connection conn = connectionFactory.createConnection();

        //3.获取seesion
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4. 找目的地，获取destination,消费端，也会从这个目的地获取消息
        Queue queue = session.createQueue("uesr");
        //51.消息创建者
        MessageProducer producer = session.createProducer(queue);
        //	producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // consumer -> 消费者
        // producer -> 创建者
        //52.创建消息
        for (int i = 0; i < 1000; i++) {
            TextMessage textMessage = session.createTextMessage("hi:" + i);
            //5.3向目的地写入消息
            if (i % 4 == 0) {
                // 设置消息的优先级
                // 对producer 整体设置
                //	producer.setPriority(9);
                //	producer.send(textMessage,DeliveryMode.PERSISTENT,9,1000 * 100);
                textMessage.setJMSPriority(9);
            }
            producer.send(textMessage);
        }

        //6.关闭连接
        conn.close();
        System.out.println("System exit....");
    }
}
