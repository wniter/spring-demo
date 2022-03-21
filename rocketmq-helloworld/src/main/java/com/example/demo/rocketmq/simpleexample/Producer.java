package com.example.demo.rocketmq.simpleexample;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @create 2022-01-21 23:47
 */

public class Producer {
    public static void main(String[] args) throws Exception{
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.31.132:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);
        }
        //Wait for sending to complete
        Thread.sleep(5000);
        producer.shutdown();
    }

//    private static int messageCount = 100;
//    private static final CountDownLatch countDownLatch = new CountDownLatch(messageCount);

    //发送异步消息
//    public static void main(String[] args) throws Exception {
//        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_name");
//        producer.setNamesrvAddr("192.168.31.132:9876");
//        producer.start();
//
//        producer.setRetryTimesWhenSendAsyncFailed(0);
//
//        for (int i = 0; i < messageCount; i++) {
//            final int index = 1;
//            Message message = new Message(
//                    "Jodie_topic_1023",
//                    "TagA",
//                    "OrderID188",
//                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
//            producer.send(message, new SendCallback() {
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    countDownLatch.countDown();
//                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
//                }
//
//                @Override
//                public void onException(Throwable e) {
//                    countDownLatch.countDown();
//                    System.out.printf("%-10d Exception %s %n", index, e);
//                    e.printStackTrace();
//                }
//            });
//            countDownLatch.await(5, TimeUnit.SECONDS);
//            producer.shutdown();
//        }
//    }
//    //发送同步message
//    public static void main(String[] args) throws Exception {
//        //使用生产者组名实例化。
//        DefaultMQProducer producer =
//                new DefaultMQProducer("please_rename_unique_name");
//        //指定名称服务器地址。
//        producer.setNamesrvAddr("192.168.31.132:9876");
//        //启动
//        producer.start();
//        for (int i = 0; i < 100; i++) {
//            //创造一个消息实例，指定topic tag and message
//            Message message = new Message(
//                    //topic
//                    "TopicTest",
//                    //tag
//                    "TagA",
//                    //message
//                    ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//
//            //调用send message发送消息给其中一个broker。
//            SendResult sendResult = producer.send(message);
//            System.out.println(sendResult);
//        }
//        //生产者实例不再使用时关闭。
//        producer.shutdown();
//
//    }
}
