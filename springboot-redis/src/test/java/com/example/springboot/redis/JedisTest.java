package com.example.springboot.redis;



import com.example.springboot.redis.service.StudentService;
import com.example.springboot.redis.service.StudentServiceImpl;
import com.example.springboot.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

/**
 * @create 2022-03-07 13:50
 */
@SpringBootTest
@Slf4j
public class JedisTest {
    public boolean connection() {
        Jedis jedis = new Jedis("192.168.31.129", 6379, 500);
        //添加密码
        jedis.auth("123456");
        if (jedis.ping().equals("PONG")) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 添加依赖
     * <dependency>
     * <groupId>redis.clients</groupId>
     * <artifactId>jedis</artifactId>
     * <version>4.0.1</version>
     * </dependency>
     * 测试redis的连接方法
     */
    @Test
    public void JedisTest() {
        Jedis jedis = new Jedis("192.168.31.129", 6379, 500);
        //添加密码
        jedis.auth("123456");
        System.out.println(jedis.ping());//PONG
    }

    /**
     * 测试事务
     */
    @Test
    public void affair() {
        Jedis jedis = new Jedis("192.168.31.129", 6379, 500);
        //添加密码
        jedis.auth("123456");
        //刷新DB
        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hello", "world");
            jsonObject.put("hello01", "world01");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        //开启事务
//        Transaction multi = jedis.multi();
//        String result = jsonObject.toJSONString();
//        try {
//            multi.set("user1", result);
//            multi.set("user2", result);
//            int i = 1 / 0; // 模拟异常
//            multi.exec(); // 执行事务
//        } catch (Exception e) {
//            multi.discard(); // 放弃事务
//            e.printStackTrace();
//        } finally {
//            System.out.println(jedis.get("user1")); // 正常执行时{"name":"xxx","hello":"world"}   // null
//            System.out.println(jedis.get("user1"));
//            jedis.close(); // 关闭链接
//        }
    }
    @Autowired
    StudentServiceImpl studentService = new StudentServiceImpl();

    @Autowired
    RedisUtils redisUtils = new RedisUtils();
    @Test
    public void redisTest() throws Exception{

//        new redisTest().Testredis();
        String key  ="11";
//        String s = studentService.hasValue(key);

        redisUtils.ListRedisDemo();


    }


}
