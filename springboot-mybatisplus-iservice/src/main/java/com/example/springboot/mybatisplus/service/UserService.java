package com.example.springboot.mybatisplus.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.mybatisplus.entity.User;
import com.example.springboot.mybatisplus.utils.PageUtils;

import java.util.Map;

public interface UserService  extends IService<User> {
    //一个封装的分页查询方法
    public PageUtils queryPage(Map<String, Object> params);
}
