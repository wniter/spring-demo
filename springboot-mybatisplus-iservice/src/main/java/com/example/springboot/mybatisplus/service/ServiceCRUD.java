package com.example.springboot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.mybatisplus.entity.User;

/**
 * service层需要继承IService，当然实现层也要继承对应的实现类。
 * 继承ISerivice
 */

public interface ServiceCRUD extends IService<User> {


//    @Autowired
//    UserMapper userMapper;
//
//    /**
//     * 插入数据
//     * @param user
//     */
//    void insert(User user) {
//        userMapper.insert(user);
//    }
}
