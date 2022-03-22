package com.example.demo.oauth;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@Configuration
@SpringBootTest
class DemoOauth2QqApplicationTests {

    @Test
    void contextLoads() {
    }

//    @BeforeAll
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    /**
//     * RestTemplate是一个执行HTTP请求的同步阻塞式工具类，它仅仅只是在 HTTP 客户端库
//     * （例如 JDK HttpURLConnection，Apache HttpComponents，okHttp 等）基础上，封装了更加简单易用的模板方法 API，
//     * 方便程序员利用已提供的模板方法发起网络请求和处理，能很大程度上提升我们的开发效率
//     */
//    @Test
//    public void simpleTest() {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://jsonplaceholder.typicode.com/posts/1";
//        String str = restTemplate.getForObject(url, String.class);
//        System.out.println(str);
//    }
}
