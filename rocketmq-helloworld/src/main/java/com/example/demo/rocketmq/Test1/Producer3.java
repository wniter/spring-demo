package com.example.demo.rocketmq.Test1;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 消息发送者
 * @author 一明哥
 *
 */
public class Producer3 {

	public static void main(String[] args)throws Exception {

		DefaultMQProducer producer = new DefaultMQProducer("pgp01");

		producer.setNamesrvAddr("192.168.150.113:9876");


		producer.start();

		producer.send(new Message("xxoo007", "hi!".getBytes()));
	//	producer.shutdown();
		System.out.println("已经停机");
		
	}
}
