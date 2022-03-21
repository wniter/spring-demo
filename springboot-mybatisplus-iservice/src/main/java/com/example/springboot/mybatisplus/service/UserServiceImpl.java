package com.example.springboot.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.mybatisplus.entity.User;
import com.example.springboot.mybatisplus.mapper.UserMapper;
import com.example.springboot.mybatisplus.utils.PageUtils;
import com.example.springboot.mybatisplus.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<User> page = this.page(
                new Query<User>().getPage(params),
                new QueryWrapper<User>()
        );

        return new PageUtils(page);
    }
}
