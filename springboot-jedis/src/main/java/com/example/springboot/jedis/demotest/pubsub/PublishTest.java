package com.example.springboot.jedis.demotest.pubsub;

import redis.clients.jedis.Jedis;


public class PublishTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.publish("qingshan-123", "666");
        jedis.publish("qingshan-abc", "pengyuyan");
    }
}
