package com.example.springboot.async.controller;


import com.example.springboot.async.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 同步方式，也就是单线程的方式来执行的话，可能会出现执行超时等异常造成请求结果失败，及时成功，前端也需要等待较长时间来获取响应结果
 */
@RestController
public class TestController {

    @Autowired
    private TestService service;


    /**
     * 使用传统方式测试
     */
    @GetMapping("/test")
    public void test() {
        System.out.println("获取主线程名称：" + Thread.currentThread().getName());
        service.serviceTest();
        System.out.println("执行成功，返回结果");
    }
    /**
     * 使用传统方式测试
     */
    @GetMapping("/test01")
    public void test01() {
        System.out.println("获取主线程名称：" + Thread.currentThread().getName());
        /**
         *  这里也可以采用以下方式使用，但是使用线程池的方式可以很便捷的对线程管理（提高程序的整体性能），
         *  也可以减少每次执行该请求时都需要创建一个线程造成的性能消耗
         *  new Thread(() ->{
         *  run方法中的业务逻辑
         *  })
         */

        /**
         * 定义一个线程池
         * 核心线程数（corePoolSize）:1
         * 最大线程数（maximumPoolSize）: 1
         * 保持连接时间（keepAliveTime）：50000
         * 时间单位 (TimeUnit):TimeUnit.MILLISECONDS(毫秒)
         * 阻塞队列 new LinkedBlockingQueue<Runnable>()
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 50000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        // 执行业务逻辑方法serviceTest()
        executor.execute(new Runnable() {
            @Override
            public void run() {
                service.serviceTest();
            }
        });
        System.out.println("执行完成，向用户响应成功信息");
    }
}

