package com.example.springboot.thymeleaf.service.impl;

import com.example.springboot.thymeleaf.entity.User;
import com.example.springboot.thymeleaf.mapper.UserMapper;
import com.example.springboot.thymeleaf.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-03-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
