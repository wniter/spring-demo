package com.example.springboot.restful.controller;


import com.example.springboot.restful.entity.User;
import com.example.springboot.restful.mapper.UserMapper;
import com.example.springboot.restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

/**
 * Spring Boot 实现Restful 方案
 *
 *Srping Boot 提供了与Rest 操作方式（GET、POST、PUT、DELETE）对应的注解：
 * 1、@GetMapping，处理 Get 请求
 * 2、@PostMapping，处理 Post 请求
 * 3、@PutMapping，用于更新资源
 * 4、@DeleteMapping，处理删除请求
 * 5、@PatchMapping，用于更新部分资源
 * Spring Boot 快速实现Restful
 *
 * /user POST 创建用户
 * /user/id GET 根据 id 获取用户信息
 * /user PUT 更新用户
 * /user/id DELETE 根据 id删除对应的用户
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public int add(User user) {
        return userMapper.insert(user);
    }

    @RequestMapping("/user/{id}")
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @RequestMapping("/delete01")
    public int delete01(Long[] id) {
        return userMapper.deleteBatchIds(Arrays.asList(id));
    }

    @RequestMapping("/update")
    public int update(User user) {
        return userMapper.updateById(user);
    }

    @RequestMapping("/select")
    public User select(Integer id) {
        return userMapper.selectById(id);
    }
}

