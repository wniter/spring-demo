package com.example.springboot.webflux.service.impl;

import com.example.springboot.webflux.entity.User;
import com.example.springboot.webflux.mapper.UserMapper;
import com.example.springboot.webflux.service.UserService;
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
