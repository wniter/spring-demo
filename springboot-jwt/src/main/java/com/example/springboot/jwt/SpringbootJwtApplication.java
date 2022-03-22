package com.example.springboot.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 认证流程如下：
 *
 * 用户使用账号和密码发出post请求；
 * 服务器使用私钥创建一个jwt；
 * 服务器返回这个jwt给浏览器；
 * 浏览器将该jwt串在请求头中像服务器发送请求；
 * 服务器验证该jwt；
 * 返回响应的资源给浏览器。
 */
@SpringBootApplication
public class SpringbootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtApplication.class, args);
    }

}
