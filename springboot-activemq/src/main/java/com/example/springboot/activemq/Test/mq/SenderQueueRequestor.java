package com.example.springboot.activemq.Test.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SenderQueueRequestor {
	public static void main(String[] args) throws Exception{

		// 1.获取连接工厂
		

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"admin",
				"admin",
				"nio://localhost:5671"
				);
		ActiveMQConnection connection = (ActiveMQConnection)connectionFactory.createConnection();
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		connection.start();
		Queue queue = session.createQueue("xxoo");
		
		MessageProducer producer = session.createProducer(queue);
		
		QueueRequestor queueRequestor = new QueueRequestor(session, queue);
		// 想 broker发送请求，等待响应
		System.out.println("=== 准备发请求");
		TextMessage responseMsg = (TextMessage)queueRequestor.request(session.createTextMessage("xxx"));
		System.out.println("=== 请求发完了");
		
		System.out.println("responseMsg:" + responseMsg.getText());
		        
		System.out.println("System exit....");
		
	}
	
}
