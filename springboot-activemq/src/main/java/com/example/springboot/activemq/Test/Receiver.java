package com.example.springboot.activemq.Test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息接收
 *
 * @create 2022-01-18 0:59
 */
public class Receiver {
    public static void main(String[] args) throws Exception {
        //1.获取连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(

                "admin",
                "admin",
                "tcp://localhost:61616"
        );
        //2.获取一个向ActiveMq的连接
        Connection connection = connectionFactory.createConnection();

        connection.start();
        //3.获取Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.找目的地，获取destination

        Destination queue = session.createQueue("user");
        // 5.获取消息
        MessageConsumer consumer = session.createConsumer(queue);

        for(int i=0;;i++) {

            TextMessage message = (TextMessage)consumer.receive();
            System.out.println("-----");
            System.out.println("message2:" + message.getText());
            //	System.out.println("metadata:" + message);
            //ActiveMQTextMessage {commandId = 303, responseRequired = false, messageId = ID:DESKTOP-OEK4RCQ-12451-1578750463269-1:1:1:1:100, originalDestination = null, originalTransactionId = null, producerId = ID:DESKTOP-OEK4RCQ-12451-1578750463269-1:1:1:1, destination = queue://user, transactionId = TX:ID:DESKTOP-OEK4RCQ-12451-1578750463269-1:1:100, expiration = 0, timestamp = 1578750760787, arrival = 0, brokerInTime = 1578750760787, brokerOutTime = 1578750981647, correlationId = null, replyTo = null, persistent = true, type = null, priority = 4, groupID = null, groupSequence = 0, targetConsumerId = null, compressed = false, userID = null, content = null, marshalledProperties = null, dataStructure = null, redeliveryCounter = 2, size = 0, properties = null, readOnlyProperties = true, readOnlyBody = true, droppable = false, jmsXGroupFirstForConsumer = false, text = hi: 99}

            // 事务中的一个操作
            //	message.acknowledge();
            // commit()
            //	if(i % 4 == 0)
            //		session.commit();

        }
    }
}
