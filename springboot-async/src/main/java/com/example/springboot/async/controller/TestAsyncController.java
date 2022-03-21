package com.example.springboot.async.controller;

import com.example.springboot.async.service.TestAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1.使用线程池的方式来实现
 * 定义TestAsyncController中的test()方法
 * 2.使用注解@EnableAsync和@Async来实现
 * 3. 使用消息队列（mq）来实现
 */
@RestController
public class TestAsyncController {

    @Autowired
    private TestAsyncService asyncService;

    /**
     * 使用传统方式测试
     */
    @GetMapping("/async")
    public void test() {
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
                asyncService.serviceTest();
            }
        });
        System.out.println("执行完成，向用户响应成功信息");
    }
}
