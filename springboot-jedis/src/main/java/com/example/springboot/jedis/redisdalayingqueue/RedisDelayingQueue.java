package com.example.springboot.jedis.redisdalayingqueue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * # 异步消息队列
 * <p>
 * 使用 redis 的 list 数据结构可以实现简单的消息队列效果。redis 消息队列没有 ask 机制，如果对消息的可靠性有严格要求则应该使用专业的消息队列中间件如 rabbitmq、kafka。
 * <p>
 * 要使用 redis 的消息队列可以通过搭配 `rpush、lpop` 或 `lpush、rpop` 命令实现。
 * <p>
 * 生产者通过 rpush、lpush 推送消息到 list 中，消费者不断轮询 key，通过 rpop、lpop 获取消息。
 * <!-- more -->
 * # 队列为空带来的问题
 * <p>
 * 客户端通过队列的 pop 指令获取消息，处理消息，再获取再处理，如此循环。如果队列空了，客户端就会一直进行无效的 pop，这样会拉高服务器的 cpu 消耗如何避免呢？
 * <p>
 * # 阻塞读
 * <p>
 * 使用 blpop/brpop 指令代替 lpop/rpop 指令。b 是 blocking 的意思，阻塞读在没有数据的时候，会进入到休眠状态，当一有数据到来会立刻立刻醒过来。
 * <p>
 * 需要注意的是，线程一直阻塞着等来消息过来可能会被当作是闲置线程，闲置过久服务器一般会主动断开连接以减少闲置资源占用。此时 blpop/brpop 会抛出异常，所以编写客户端消费者时需要小心，当捕获此异常时，需要重试。
 * <p>
 * # 延迟队列
 * <p>
 * 使用 redis 的 zset 可以实现延迟队列。消息体存为 value，消息的处理时间作为 score，使用多个线程轮询 zset 获取即将过期的任务进行处理。多个线程是为了保障可用性，万一一个线程挂掉了其他线程可以继续处理。
 * <p>
 * 有了多个线程，就需要确保并发争抢任务的问题，确保任务不会被多次执行。
 */
public class RedisDelayingQueue<T> {
    static class TaskItem<T> {
        public String id;
        public T msg;
    }
    private Type taskType = new TypeReference<TaskItem<T>>(){}.getType();

    private String queueKey;
    private Jedis jedis;

    public RedisDelayingQueue(Jedis jedis, String demo) {
        this.jedis = jedis;
        this.queueKey = demo;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();

        RedisDelayingQueue<String> queue = new RedisDelayingQueue<>(jedis, "demo");

        Thread producer = new Thread(() ->{
            for (int i = 0;i< 10;i++) {
                queue.delay("code" + i);
            }
        });
        Thread consumer = new Thread(queue::loop);

        producer.start();
        consumer.start();
        try {
            producer.join();
            System.out.println("等 producer 先执行完，再执行 consumer...");
        } catch (InterruptedException ignored) {
        }
    }

    private void loop() {
        System.out.println("> consumer");
        while (!Thread.interrupted()) {
            // 以当前时间为截止时间作为 score 去查 zset。
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String messageBody = values.iterator().next();
            // zrem 方法是多线程任务只执行一次的关键。删除是原子操作，如果删除失败，说明其他线程已经消费了此消息。
            if (jedis.zrem(queueKey, messageBody) > 0) {
                TaskItem<T> task = JSON.parseObject(messageBody, taskType);
                this.handleMsg(task.msg);
            }
        }
    }

    private void delay(T msg) {
        System.out.println("> producer");
        TaskItem<T> task = new TaskItem<>();
        task.msg = msg;
        String messageBody = JSON.toJSONString(task);
        // 放入延迟队列，5s后开始被消费
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, messageBody);

    }

    private void handleMsg(T task) {
        System.out.println("处理消息，task：" + task);
    }
}
