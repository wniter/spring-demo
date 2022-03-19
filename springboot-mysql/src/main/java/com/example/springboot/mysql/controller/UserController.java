package com.example.springboot.mysql.controller;

import com.example.springboot.mysql.entity.User;
import com.example.springboot.mysql.service.UserService;
import com.example.springboot.mysql.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @create 2022-03-20 2:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public Result<List<User>> getAllUser(){
        List<User> UserList = userService.getAllUser();
        return new Result<>(true, 200, "查询成功",UserList);
    }


    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id){
        User User = userService.getUserById(id);
        return new Result<>(true, 200,"查询成功",User);
    }


    @DeleteMapping("/delete/{id}")
    public Result<Integer> deleteUserById(@PathVariable("id") Integer id){
        int i = userService.delete(id);
        return new Result<>(true,200,"删除成功",i);
    }


    @PutMapping("/{id}")
    public Result<Integer> updateUser(@RequestBody User User, @PathVariable("id") Integer id){
        User.setId(id);
        int i = userService.update(User);
        return new Result<>(true, 200, "更新成功",i);
    }


    @PostMapping
    public Result<Integer> addUser(@RequestBody User User){
        int i = userService.add(User);
        return new Result<>(true, 200, "添加成功",i);
    }
}
