package com.example.springboot.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.springboot.rocketmq.*"})
public class SpringbootRocketmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRocketmqApplication.class, args);
    }
//http://localhost:1100/MQTest/sendMessage?message=123121
}
