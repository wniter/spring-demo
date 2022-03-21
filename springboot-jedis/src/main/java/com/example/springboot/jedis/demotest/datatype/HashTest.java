package com.example.springboot.jedis.demotest.datatype;



import com.example.springboot.jedis.demotest.util.JedisUtil;

import java.util.ArrayList;
import java.util.List;


public class HashTest {
    public static void main(String[] args) {
        String h1 = JedisUtil.getJedisUtil().hget("h1", "a");
        System.out.println(h1);

        List<String> list = new ArrayList<String>();
        list = JedisUtil.getJedisUtil().hmget("h1","a","b","c","d","e");
        System.out.println(list);
    }
}
