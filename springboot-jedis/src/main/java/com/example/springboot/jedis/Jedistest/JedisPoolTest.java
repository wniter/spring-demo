package com.example.springboot.jedis.Jedistest;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisPool 连接池
 */
public class JedisPoolTest {
    /**
     * host
     */
    private static String ADDR = "192.168.131.129";
    /**
     * password
     */
    private static String AUTH = "123456";
    /**
     * port
     */
    private static int PORT = 6379;

    private static JedisPool jedisPool = null;
    private static int TIMEOUT = 10000;
    /**
     * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
     */
    private static int MAX_WAIT = 10000;
    /**
     * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
     */
    private static boolean TEST_ON_BORROW = true;
    /**
     * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
     */
    private static int MAX_IDLE = 200;

    /**
     * 初始化redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(MAX_IDLE);
            config.setTestOnBorrow(TEST_ON_BORROW);
            config.setMaxWaitMillis(MAX_WAIT);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 过去jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        if (jedisPool != null) {
            Jedis jedis = jedisPool.getResource();
            return jedis;
        }
        return null;
    }

    /**
     * 释放资源
     * @param jedis
     */
    public synchronized static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
