package com.example.springboot.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 1，Fastjson中的经常调用的方法:
 * <p>
 * 1 public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
 * 2 public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
 * 3 public static final T parseObject(String text, Class clazz);// 把JSON文本parse为JavaBean
 * 4 public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
 * 5 public static final List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
 * 6 public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
 * 7 public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
 * 8 public static final Object toJSON(Object javaObject); //将JavaBean转换为JSONObject或者JSONArray。
 * <p>
 * <p>
 * <p>
 * 2，Fastjson字符串转List<Map<String,Object>>(), 或者List<String>()的用法;
 * List<Map<String, Object>> list = JSONObject.parseObject(respJson, new TypeReference<List<Map<String, Object>>>() {});
 * <p>
 * <p>
 * <p>
 * 3，Fastjson的SerializerFeature序列化属性
 * QuoteFieldNames———-输出key时是否使用双引号,默认为true
 * WriteMapNullValue——–是否输出值为null的字段,默认为false
 * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
 * 例如：JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue);
 */
public class Main {
    public static void main(String[] args) {
        Test();

    }

    private static void Test() {
        List<Person> list = new ArrayList<>();
        list.add(new Person(0, "1", new Date()));
        list.add(new Person(1, "2", new Date()));
        System.out.println(list);
        System.out.println("---------------");
        String parse = JSON.toJSONString(list);
        System.out.println(parse);
        System.out.println("-----------");
        List<Person> parse1 = JSON.parseArray(parse).toJavaList(Person.class);
        System.out.println(parse1);

    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Person {

    @JSONField(name = "AGE")
    private int age;

    @JSONField(name = "FULL NAME")
    private String fullName;

    @JSONField(name = "DATE OF BIRTH")
    private Date dateOfBirth;
}
