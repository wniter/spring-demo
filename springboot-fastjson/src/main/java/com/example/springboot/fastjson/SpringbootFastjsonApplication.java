package com.example.springboot.fastjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootFastjsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFastjsonApplication.class, args);
        System.out.println("http://localhost:8080/json");
    }

}
