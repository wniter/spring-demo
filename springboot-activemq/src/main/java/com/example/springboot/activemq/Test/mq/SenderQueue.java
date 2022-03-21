
package com.example.springboot.activemq.Test.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送
 * @author 一明哥
 *
 */
public class SenderQueue {

	public static void main(String[] args) throws Exception{

		// 1.获取连接工厂
		

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"admin",
				"admin",
				"nio://localhost:5671"
				);
		// 2.获取一个向ActiveMQ的连接
		// 2.1 设置同步异步发送消息
		// connectionFactory.setSendAcksAsync(true);
		Connection connection = connectionFactory.createConnection();
//		ActiveMQConnection activeMQConnection = (ActiveMQConnection)connection;
//		activeMQConnection.setUseAsyncSend(true);
		// 3.获取session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 4. 找目的地，获取destination，消费端，也会从这个目的地取消息
		
		
		connection.start();
		Queue queue = session.createQueue("xxoo");
		
		MessageProducer producer = session.createProducer(queue);
		
		
		// 发送一条消息给指定消费者
        Message message = session.createTextMessage("Message from ServerA xxx" );
        
        producer.send(message); 
        
		System.out.println("System exit....");
		
	}
	
		
	
}
