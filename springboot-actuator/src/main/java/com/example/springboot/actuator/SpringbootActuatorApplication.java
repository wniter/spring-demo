package com.example.springboot.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActuatorApplication.class, args);
        System.out.println("http://localhost:8080/actuator");
    }

}
