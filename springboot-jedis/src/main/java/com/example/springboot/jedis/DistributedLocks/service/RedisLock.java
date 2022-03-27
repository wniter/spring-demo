package com.example.springboot.jedis.DistributedLocks.service;

/**
 * 使用分布式锁，是为了解决并发情况下相同代码块被多个线程同时执行带来的数据错乱问题。使用分布式锁以保证同一个代码块只有一个线程被执行。
 *
 * 超时问题
 * 如果加锁的代码块执行时间太久，锁自动释放了。加锁的代码块还没执行完毕第二个线程提前拿到了锁，会导致原来加锁的代码块得不到严格的串行执行。要规避这种问题，应该尽量避免加锁代码块用于执行时间过长的任务，合理配置锁的过期时间。
 * 超时还会导致另一个问题，当前线程可能会释放另一个线程的锁。线程A由于执行时间过长锁被自动释放，线程B拿到锁还没执行完，线程A代码块执行完后调用 del 指令释放锁，然而此时释放的却是线程B的锁。要规避这种问题，我们可以设置 set 指令的 value 参数为一个随机数（或当前线程名），删除时传入这个随机数，这样用来避免当前线程的锁被其他线程释放。
 * 伪代码如下，其中 if 分支判断和 del 操作不是原子操作，如果进入 if 分支后系统挂了锁就不会执行 del 指令了。（不过当前锁已过期了，但是保证了当前线程不会释放其他线程的锁。）
 *
 */
public interface RedisLock {
    boolean lock (String key);

    boolean lock(String key, Integer expire);

    boolean lock(String key, String value);

    boolean lock(String key, String value, Integer expire);
    boolean unlock(String key);

    boolean unlock(String key, String value);

    Long expire(String key, Integer expire);
}
