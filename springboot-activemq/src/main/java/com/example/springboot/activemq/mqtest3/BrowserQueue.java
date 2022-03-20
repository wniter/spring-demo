package com.example.springboot.activemq.mqtest3;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * 消息接受
 *
 * @create 2021-12-07 22:16
 */
public class BrowserQueue {
    public static void main(String[] args) throws Exception{
        // 1.获取连接工厂

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://localhost:5671"
        );
        // 1.1 添加信任的 持久化类型

        ArrayList<String> list = new ArrayList<String>();
        list.add(Girl.class.getPackage().getName());

        connectionFactory.setTrustedPackages(list);

        // 2.获取一个向ActiveMQ的连接
        Connection connection = connectionFactory.createConnection();
        System.out.println("ReceiverQueue-1 2  Started");
        connection.start();
        // 3.获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 找目的地，获取destination，消费端，也会从这个目的地取消息


        MessageConsumer consumer = session.createConsumer(new ActiveMQQueue("xxoo"));

        QueueBrowser browser = session.createBrowser(new ActiveMQQueue("xxoo"));

        Enumeration enumeration = browser.getEnumeration();

        while (enumeration.hasMoreElements()) {
            TextMessage textMessage = (TextMessage) enumeration.nextElement();

            System.out.println("textMessage:" + textMessage.getText());
        }



//		consumer.setMessageListener(new MessageListener() {
//
//			public void onMessage(Message message) {
//				// TODO Auto-generated method stub
//
//			}
//		});
    }
}
