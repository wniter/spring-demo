package com.example.springboot.actuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


//53.8.2编写自定义HealthIndicators
//@Component
//public class MyHealthIndicator implements HealthIndicator {
//    @Override
//    public Health health() {
//        int errorCode = check();// // perform some specific health check
//        if (errorCode != 0) {
//            return Health.down().withDetail("Error Code", errorCode)
//                    .build();
//        }
//        return Health.up().build();
//    }
//    //要提供自定义健康信息，您可以注册实现该HealthIndicator界面的Spring beans
//    // 您需要提供health()方法的实现并返回Health响应。Health响应应包含状态
//    public static int check(){
//        return 0;
//    }
//}
