package com.example.springboot.mysql.service;

import com.example.springboot.mysql.entity.User;

import java.util.List;

/**
 * @create 2022-03-20 2:52
 */
public interface UserService {
    public int add(User user);
    public int update(User user);
    public int delete(Integer id);
    public User getUserById(Integer id);
    public List<User> getAllUser();
}
