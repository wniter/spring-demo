//package com.example.springboot.redis.redistest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class redisTest {
//    public redisTest() {
//    }
//
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Autowired
//    @Qualifier("ooxx")
//    StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    public void Testredis() throws Exception {
//
//        //添加key-value
//        stringRedisTemplate.opsForValue().set("hello", "world");
//        System.out.println(stringRedisTemplate.opsForValue().get("hello"));
//
//        RedisConnection conn = redisTemplate.getConnectionFactory().getConnection();
//
//        conn.set("hello1".getBytes(), "world1".getBytes());
//
////redis中opsForValue方法得使用介绍
//        redisTemplate.opsForValue().set("stringvalue", "bbb");
//        //获取key键对应的值
//        String stringvalue = (String) redisTemplate.opsForValue().get("key");
////在原有的值基础上新增字符串到末尾
//        redisTemplate.opsForValue().append("key", "appendValue");
//        String stringValueAppend = (String) redisTemplate.opsForValue().get("key");
//        System.out.println(stringValueAppend);
//        //截取key键对应值得字符串，从开始下标位置开始到结束下标得位置得字符串
//        String cutString = redisTemplate.opsForValue().get("key", 0, 3);
//        //获取原来Key键对应的值并重新赋新值
//        String oldAndNewStringValue = (String) redisTemplate.opsForValue().getAndSet("key", "cc");
//        System.out.println(oldAndNewStringValue);
//        String newStringValue = (String) redisTemplate.opsForValue().get("key");
//        System.out.println(newStringValue);
//
//        redisTemplate.opsForValue().setBit("key", 1, false);
//
//
//    }
//
//
//////
//////        stringRedisTemplate.opsForValue().set("hello01","china");
//////
//////        System.out.println(stringRedisTemplate.opsForValue().get("hello01"));
////
////    RedisConnection conn = redisTemplate.getConnectionFactory().getConnection();
////
////        conn.set("hello02".getBytes(),"mashibing".getBytes());
////        System.out.println(new String(conn.get("hello02".getBytes())));
////
////
//////        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//////        hash.put("sean","name","zhouzhilei");
//////        hash.put("sean","age","22");
//////
//////        System.out.println(hash.entries("sean"));
////
////
////    Person p = new Person();
////        p.setName("zhangsan");
////        p.setAge(16);
////
//////        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
////
////    Jackson2HashMapper jm = new Jackson2HashMapper(objectMapper, false);
////
////        stringRedisTemplate.opsForHash().putAll("sean01",jm.toHash(p));
////
////    Map map = stringRedisTemplate.opsForHash().entries("sean01");
////
////    Person per = objectMapper.convertValue(map, Person.class);
////        System.out.println(per.getName());
////
////
////        stringRedisTemplate.convertAndSend("ooxx","hello");
////
////    RedisConnection cc = stringRedisTemplate.getConnectionFactory().getConnection();
////        cc.subscribe(new MessageListener() {
////        @Override
////        public void onMessage(Message message, byte[] pattern) {
////            byte[] body = message.getBody();
////            System.out.println(new String(body));
////        }
////    }, "ooxx".getBytes());
////
////        while(true){
////        stringRedisTemplate.convertAndSend("ooxx","hello  from wo zi ji ");
////        try {
////            Thread.sleep(3000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////
////    }
//
//}
