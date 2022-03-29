package com.example.springboot.async.task;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @create 2021-11-28 0:07
 */
@Component
@Slf4j
@Configuration
public class Taskfactory {
    //模拟异步任务
    @Async
    public Future<Boolean> asyncTask(String taskname, int time) throws InterruptedException {
        doTask(taskname, time);
        return new AsyncResult<>(Boolean.TRUE);
    }

    //模拟同步任务
    public void Task(String name, int time) throws Exception {
        doTask(name, time);
    }

    private void doTask(String taskname, int time) throws InterruptedException {
        log.info("{}开始执行时间，当前线程名称{}", taskname, Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(time);
        log.info("{}结束执行时间，当前线程名称{}", taskname, Thread.currentThread().getName());


    }
    /**
     * 模拟5秒的异步任务
     */
    @Async
    public Future<Boolean> asyncTask1() throws InterruptedException {
        doTask("asyncTask1", 5);
        return new AsyncResult<>(Boolean.TRUE);
    }

    /**
     * 模拟2秒的异步任务
     */
    @Async
    public Future<Boolean> asyncTask2() throws InterruptedException {
        doTask("asyncTask2", 2);
        return new AsyncResult<>(Boolean.TRUE);
    }

    /**
     * 模拟3秒的异步任务
     */
    @Async
    public Future<Boolean> asyncTask3() throws InterruptedException {
        doTask("asyncTask3", 3);
        return new AsyncResult<>(Boolean.TRUE);
    }

    /**
     * 模拟5秒的同步任务
     */
    public void task1() throws InterruptedException {
        doTask("task1", 5);
    }

    /**
     * 模拟2秒的同步任务
     */
    public void task2() throws InterruptedException {
        doTask("task2", 2);
    }

    /**
     * 模拟3秒的同步任务
     */
    public void task3() throws InterruptedException {
        doTask("task3", 3);
    }

    private void doTask1(String taskName, Integer time) throws InterruptedException {
        log.info("{}开始执行，当前线程名称【{}】", taskName, Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(time);
        log.info("{}执行成功，当前线程名称【{}】", taskName, Thread.currentThread().getName());
    }

}
