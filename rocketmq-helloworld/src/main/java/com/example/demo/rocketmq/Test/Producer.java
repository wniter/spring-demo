package com.example.demo.rocketmq.Test;

import io.netty.handler.codec.mqtt.MqttMessageIdAndPropertiesVariableHeader;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @create 2022-01-22 19:35
 */
public class Producer {
    public static void main(String[] args) throws  Exception {
        DefaultMQProducer producer = new DefaultMQProducer("xxoo");

        producer.setNamesrvAddr("192.168.31.132:9876");
        //发送消息
        // topic 消息将要发送到的地址
        // body  消息中的具体数据
        Message message= new Message("mytopic001","the first message".getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message message1 = new Message("mytopic002","the second message".getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message message2 = new Message("mytopic003","the three message".getBytes(RemotingHelper.DEFAULT_CHARSET));

        ArrayList<Message> list = new ArrayList<>();
        list.add(message);
        list.add(message1);
        list.add(message2);
        // 同步消息发送
        // for
        // list.add
        SendResult sendResult = producer.send(list);

        System.out.println(sendResult);
        producer.shutdown();
        System.out.println("已经停机");
    }
}
