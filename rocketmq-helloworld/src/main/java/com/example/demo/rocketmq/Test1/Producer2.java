package com.example.demo.rocketmq.Test1;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * 消息发送者
 * @author 一明哥
 *
 */
public class Producer2 {

	public static void main(String[] args)throws Exception {

		DefaultMQProducer producer = new DefaultMQProducer("xoxogp1");

		producer.setNamesrvAddr("192.168.150.113:9876");


		// 回调



		producer.start();


		//几次
		producer.setRetryTimesWhenSendFailed(2);
//		producer.send(msg);
//		producer.setRetryAnotherBrokerWhenNotStoreOK();

	//	producer.shutdown();
		System.out.println("已经停机");
		
	}
}
