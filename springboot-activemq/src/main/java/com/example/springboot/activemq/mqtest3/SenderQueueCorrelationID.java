package com.example.springboot.activemq.mqtest3;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送
 *
 * @create 2021-12-07 23:25
 */
public class SenderQueueCorrelationID {
    public static void main(String[] args) throws Exception {
        //1.获取连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin",	"nio://localhost:5671");
        //2.获取一个向activemq的连接
        //2.1设置同步异步发送消息
        connectionFactory.setSendAcksAsync(true);
        Connection connection = connectionFactory.createConnection();
        ActiveMQConnection activeMQConnection = (ActiveMQConnection) connection;
        activeMQConnection.setUseAsyncSend(true);
        //3.获取sesion
        Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //4.找目的底，获取destination，消费端，也会从这个目的地取消息
        connection.start();
        Queue queue = session.createQueue("xxoo");

        MessageProducer producer = session.createProducer(queue);


        // 发送一条消息给指定消费者
        Message message = session.createTextMessage("Message from ServerA xxx" );

        // 粒度 细 筛选
        message.setJMSCorrelationID("xiaomovie");
        producer.send(message);

        System.out.println("System exit....");


    }
}
