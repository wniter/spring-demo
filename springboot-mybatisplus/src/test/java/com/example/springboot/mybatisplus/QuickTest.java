package com.example.springboot.mybatisplus;


import com.example.springboot.mybatisplus.entity.User;
import com.example.springboot.mybatisplus.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @create 2022-03-05 19:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@SpringBootTest
@MapperScan("com.example.springboot.mybatisplus.mapper")
public class QuickTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        System.out.println("-----insert method test---");
        //设置属性数据
        User user = new User();
        user.setAge(111);
        user.setEmail("1111");
        user.setName("111");
        userMapper.insert(user);
        System.out.println(user.toString());
        System.out.println("id =" + user.getId());
    }
}
