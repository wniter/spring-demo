package com.example.demo.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/5/16 下午12:58.
 * @blog http://blog.didispace.com
 */
@Component
public class Task {

    public static Random random =new Random();

    @Async
    public Future<String> doTaskOne() throws Exception {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }

}
//@Slf4j
//@Component
//public class Task {
//
//    public static Random random = new Random();
//
//    @Async("taskExecutor")
//    public void doTaskOne() throws Exception {
//        log.info("开始做任务一");
//        long start = System.currentTimeMillis();
//        Thread.sleep(random.nextInt(10000));
//        long end = System.currentTimeMillis();
//        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
//    }
//
//    @Async("taskExecutor")
//    public void doTaskTwo() throws Exception {
//        log.info("开始做任务二");
//        long start = System.currentTimeMillis();
//        Thread.sleep(random.nextInt(10000));
//        long end = System.currentTimeMillis();
//        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
//    }
//
//    @Async("taskExecutor")
//    public void doTaskThree() throws Exception {
//        log.info("开始做任务三");
//        long start = System.currentTimeMillis();
//        Thread.sleep(random.nextInt(10000));
//        long end = System.currentTimeMillis();
//        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
//    }
//
//}
//@Slf4j
//@Component
//public class Task {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Async("taskExecutor")
//    public void doTaskOne() throws Exception {
//        log.info("开始做任务一");
//        long start = System.currentTimeMillis();
//        log.info(stringRedisTemplate.randomKey());
//        long end = System.currentTimeMillis();
//        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
//    }
//
//    @Async("taskExecutor")
//    public void doTaskTwo() throws Exception {
//        log.info("开始做任务二");
//        long start = System.currentTimeMillis();
//        log.info(stringRedisTemplate.randomKey());
//        long end = System.currentTimeMillis();
//        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
//    }
//
//    @Async("taskExecutor")
//    public void doTaskThree() throws Exception {
//        log.info("开始做任务三");
//        long start = System.currentTimeMillis();
//        log.info(stringRedisTemplate.randomKey());
//        long end = System.currentTimeMillis();
//        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
//    }
//
//}
//@Slf4j
//@Component
//public class Task {
//
//    public static Random random = new Random();
//
//    @Async("taskExecutor")
//    public Future<String> run() throws Exception {
//        long sleep = random.nextInt(10000);
//        log.info("开始任务，需耗时：" + sleep + "毫秒");
//        Thread.sleep(sleep);
//        log.info("完成任务");
//        return new AsyncResult<>("test");
//    }
//
//}
//
//@EnableAsync
//@Configuration
//class TaskPoolConfig {
//
//    @Bean("taskExecutor")
//    public Executor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(10);
//        executor.setMaxPoolSize(20);
//        executor.setQueueCapacity(200);
//        executor.setKeepAliveSeconds(60);
//        executor.setThreadNamePrefix("taskExecutor-");
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        return executor;
//    }
//}
