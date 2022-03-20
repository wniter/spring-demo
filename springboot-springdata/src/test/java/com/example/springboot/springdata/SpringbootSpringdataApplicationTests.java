package com.example.springboot.springdata;

import com.example.springboot.springdata.entity.User;
import com.example.springboot.springdata.repository.UserRepository;

import org.junit.*;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootSpringdataApplication.class)
class SpringbootSpringdataApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        List<User> users = userRepository.findAll();
        System.out.println(users);
    }
}
