package com.example.springboot.jedis.DistributedLocks.service;

/**
 * 使用分布式锁，是为了解决并发情况下相同代码块被多个线程同时执行带来的数据错乱问题。使用分布式锁以保证同一个代码块只有一个线程被执行。
 *
 * 超时问题
 * 如果加锁的代码块执行时间太久，锁自动释放了。加锁的代码块还没执行完毕第二个线程提前拿到了锁，会导致原来加锁的代码块得不到严格的串行执行。要规避这种问题，应该尽量避免加锁代码块用于执行时间过长的任务，合理配置锁的过期时间。
 * 超时还会导致另一个问题，当前线程可能会释放另一个线程的锁。线程A由于执行时间过长锁被自动释放，线程B拿到锁还没执行完，线程A代码块执行完后调用 del 指令释放锁，然而此时释放的却是线程B的锁。要规避这种问题，我们可以设置 set 指令的 value 参数为一个随机数（或当前线程名），删除时传入这个随机数，这样用来避免当前线程的锁被其他线程释放。
 * 伪代码如下，其中 if 分支判断和 del 操作不是原子操作，如果进入 if 分支后系统挂了锁就不会执行 del 指令了。（不过当前锁已过期了，但是保证了当前线程不会释放其他线程的锁。）
 *

 ```sh
 127.0.0.1:6379> setnx lock-test 1
 (integer) 1
 // TODO do something...
 127.0.0.1:6379> del locl-test
 (integer) 0
 ```

 【问题1】
 如果逻辑执行到中间出现异常了或机器宕机，那么就导致 del 指令没有被执行，锁就永远得不到释放。我们可以通过拿到锁后给锁加一个过期时间，这样保证如果出现异常了锁也会自动释放。

 ```sh
 127.0.0.1:6379> set lock:test 1
 OK
 127.0.0.1:6379> expire lock:test 10
 (integer) 1
 // TODO do something...
 127.0.0.1:6379> del lock:test
 (integer) 0
 ```

 【问题2】
 如果 set 指令和 expire 指定执行中间，服务器宕机了，expire 得不到执行，锁得不到释放，那么也会出现死锁。这是因为 set 和 expire 是两条指令，不是一个原子操作。好在 redis2.8 后，可以通过给 set 指令添加拓展参数使得 setnx 和 expire 可以一起执行。

 ```sh
 127.0.0.1:6379> set lock:codehole 1 ex 30 nx # 相当于 setnx lock:codehole 1 & expire lock:codehole 30
 OK
 127.0.0.1:6379> set lock:codehole 1 ex 30 nx # 已存在，setex 失败。
 (nil)
 127.0.0.1:6379> del lock:codehole
 (integer) 1
 ```

 # 超时问题

 如果加锁的代码块执行时间太久，锁自动释放了。加锁的代码块还没执行完毕第二个线程提前拿到了锁，会导致原来加锁的代码块得不到严格的串行执行。要规避这种问题，应该尽量避免加锁代码块用于执行时间过长的任务，合理配置锁的过期时间。

 超时还会导致另一个问题，当前线程可能会释放另一个线程的锁。线程A由于执行时间过长锁被自动释放，线程B拿到锁还没执行完，线程A代码块执行完后调用 del 指令释放锁，然而此时释放的却是线程B的锁。要规避这种问题，我们可以设置 set 指令的 value 参数为一个随机数（或当前线程名），删除时传入这个随机数，这样用来避免当前线程的锁被其他线程释放。

 伪代码如下，其中 if 分支判断和 del 操作不是原子操作，如果进入 if 分支后系统挂了锁就不会执行 del 指令了。（不过当前锁已过期了，但是保证了当前线程不会释放其他线程的锁。）

 ```java
 String ramdonValue = "1";
 String value = jedis.get("key");
 if (ramdonValue.equals(value)) {
 jedis.del("key");
 }
 ```

 虽然在 if 分支挂掉不会误释放其他线程的锁，但是还有更优雅的方式：即使用 lua 脚本，lua 脚本中的语句会被当作一个原子操作来执行。

 ```Java
 String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] " +
 "then" +
 "   return redis.call('del', KEYS[1])" +
 "else " +
 "   return 0 " +
 "end";
 Object value = jedis.eval(luaScript, Collections.singletonList("lock:test"), Collections.singletonList("randomValue"));
 System.out.println(value);
 ```

 这种方案只是相对安全一点，如果真的超时了，其他线程就会乘虚而入。一种解决方案是给当前线程添加一个守护线程，间断的给当前锁「续命」，如果系统宕机，守护线程会随着主线程的死亡而死亡，一段时间后锁被自动释放。
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
