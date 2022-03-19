package com.example.springboot.swagger3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class SpringbootSwagger3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSwagger3Application.class, args);
        System.out.println("localhost:8081/swagger-ui/index.html");
        System.out.println("localhost:8081/doc.html");
    }

}
