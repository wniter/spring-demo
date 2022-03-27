package com.example.springboot.jedis.DistributedLocks;


import com.example.springboot.jedis.DistributedLocks.service.RedisLock;
import com.example.springboot.jedis.DistributedLocks.service.serviceImpl.RedisLockImpl;

public class UsingRedisLock {
    public static void main(String[] args) {
        new Thread(new BusinessService()).start();
        new Thread(new BusinessService()).start();
    }
}

class BusinessService implements Runnable {
    public static final RedisLock lock = new RedisLockImpl();

    public static void businessMethod() {
        String currentThread = Thread.currentThread().getName();

        // 加锁时添加了 value，释放锁时必须传入 value。否则可以释放其他线程加的锁。
        boolean success = lock.lock("lock:key", currentThread, 30);
        System.out.println(currentThread + " 获取锁？" + success);
        if (success) {
            try {
                System.out.println("\t" + currentThread + "执行业务方法。。");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                System.out.println(currentThread + " 释放锁？" + lock.unlock("lock:key", currentThread));
            }
        }
    }

    @Override
    public void run() {
        businessMethod();
    }
}
