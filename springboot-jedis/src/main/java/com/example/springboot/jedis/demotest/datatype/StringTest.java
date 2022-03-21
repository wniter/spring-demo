package com.example.springboot.jedis.demotest.datatype;


import com.example.springboot.jedis.demotest.util.JedisUtil;

/**

 * @Description: 咕泡学院，只为更好的你
 */
public class StringTest {
    public static void main(String[] args) {
        JedisUtil.getJedisUtil().set("qingshan", "2673");
        // JedisUtil.getJedisUtil().incr("qingshan");

        String qs = JedisUtil.getJedisUtil().get("qingshan");
        System.out.println(qs);


    }
}
