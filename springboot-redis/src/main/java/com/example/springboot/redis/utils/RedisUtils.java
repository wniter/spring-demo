package com.example.springboot.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;



/**
 * redis的操作工具类
 * redis 中的核心操作类为RedisTemplate，是一个父类，opsForValue()方法可以实现crud
 * redisTemplate.opsForValue(); //操作字符串
 * redisTemplate.opsForHash(); //操作hash
 * redisTemplate.opsForList(); //操作list
 * redisTemplate.opsForSet(); //操作set
 * redisTemplate.opsForZSet(); //操作有序zset
 */
@Component
public class RedisUtils {
    // 注入我们自定义的 RedisTemplate
    @Autowired
    //通过使用 @Qualifier 注解，我们可以消除需要注入哪个 bean 的问题
    @Qualifier(value = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;


    //RedisTemplate方法讲解

    /**
     * 判断指定的key的失效时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断string类型的key是否存在
     *
     * @param key
     * @return
     */
    public boolean haskey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key删除reids中缓存数据
     *
     * @param key
     * @return
     */
    public boolean deleteKey(String key) {
        if (key != null) {
            redisTemplate.delete(key);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 保存和读取String
     *
     * @return 缓存结果
     */
    public String StringRedisDemo() {
        //设置过期时间为1分钟
        redisTemplate.opsForValue().set("key1", "value1", 1, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set("key2", "value2");
        redisTemplate.opsForValue().set("key3", "value3");
        //读取redis数据
        String result1 = redisTemplate.opsForValue().get("key1").toString();
        String result2 = redisTemplate.opsForValue().get("key2").toString();
        String result3 = redisTemplate.opsForValue().get("key3").toString();
        System.out.println("缓存结果为：result：" + result1 + "  " + result2 + "   " + result3);
        return "缓存结果为：result：" + result1 + "  " + result2 + "   " + result3;
    }

    /**
     * 保存和读取list
     *redis对list操作分为左和右两种
     * redisTemplate.opsForList().leftPush()
     * 实际调用的是lPush
     * return connection.lPush(rawKey, new byte[][]{rawValue});
     * redisTemplate.opsForList().rightPush()
     * 调用的是rPush
     * lPush将数据添加到key对应的现有数据的左边，也就是头部，rPush是将现有数据添加到现有数据的右边，也就是尾部，可以根据业务的不同进行对应的添加

     * @return
     */
    public String ListRedisDemo() {
        List<String> list1 = new ArrayList<>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");
        List<String> list2 = new ArrayList<>();
        list2.add("b1");
        list2.add("b2");
        list2.add("b3");
        redisTemplate.opsForList().leftPush("listkey1", list1);
        redisTemplate.opsForList().rightPush("listkey2", list2);
        List<String> resultList1 = (List<String>) redisTemplate.opsForList().leftPop("listkey1");
        List<String> resultList2 = (List<String>) redisTemplate.opsForList().rightPop("listkey2");
        System.out.println("resultList1:" + resultList1);
        System.out.println("resultList2:" + resultList2);
        return "成功";
    }

    /**
     * Hash结构，保存和读取map
     */
    public String MapRedisDemo() {
        Map<String, String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        redisTemplate.opsForHash().putAll("map1",map);
        Map<Object, Object> resultMap = redisTemplate.opsForHash().entries("map1");
        List<Object> reslutMapList = redisTemplate.opsForHash().values("map1");
        Set<Object> resultMapSet = redisTemplate.opsForHash().keys("map1");
        String value = (String) redisTemplate.opsForHash().get("map1", "key1");
        System.out.println("value:" + value);
        System.out.println("resultMapSet:" + resultMapSet);
        System.out.println("resultMap:" + resultMap);
        System.out.println("resulreslutMapListtMap:" + reslutMapList);
        return "成功";
    }
    /**
     * 保存和读取Set
     */
    public String GetRedisDemo() {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add("key1", "value1");
        set.add("key1", "value2");
        set.add("key1", "value3");
        Set<Object> resultSet = redisTemplate.opsForSet().members("key1");
        System.out.println("resultSet:" + resultSet);
        return "resultSet:" + resultSet;
    }
    /**
     * 保存和读取zset
     */
    public String zsetRedisDemo() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-5", 9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-6", 9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset1", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
        return "成功";
    }
}
