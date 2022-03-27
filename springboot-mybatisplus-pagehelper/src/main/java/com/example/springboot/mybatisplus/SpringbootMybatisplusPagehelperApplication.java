package com.example.springboot.mybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SpringbootMybatisplusPagehelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisplusPagehelperApplication.class, args);
        //访问地址
        System.out.println("swagger地址：" + "\t" + "localhost:" +8080 + "/swagger-ui.html");
    }

}
