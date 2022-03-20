package com.example.springboot.activemq.mqtest3;


import javax.jms.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @create 2021-12-08 22:38
 */
public class MyListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else if (message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;

            try {
                Girl girl = (Girl) objectMessage.getObject();
                System.out.println(girl.getAge());
                System.out.println(girl.getName());
                System.out.println(girl.getPrice());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else if (message instanceof BytesMessage) {
            BytesMessage bytesMessage = (BytesMessage) message;
            FileOutputStream out = null;
            try {
                out = new FileOutputStream("test.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] bytes = new byte[1024];
            int len = 0;
            try {
                if (!((len = bytesMessage.readBytes(bytes)) != -1)) {
                    out.write(bytes, 0, len);
                }
            } catch (JMSException | IOException e) {
                e.printStackTrace();
            }
        } else if (message instanceof MapMessage) {
            MapMessage mapMessage = (MapMessage)message;
            System.out.println("mapMessage:" + mapMessage);
            try {
                System.out.println("mapMessage:" + mapMessage.getString("name"));
                System.out.println("mapMessage:" + mapMessage.getInt("age"));
                System.out.println("mapMessage:" + mapMessage.getDouble("price"));
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
