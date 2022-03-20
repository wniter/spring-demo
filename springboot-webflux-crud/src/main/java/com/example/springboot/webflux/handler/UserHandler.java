package com.example.springboot.webflux.handler;

import com.example.springboot.webflux.entity.User;
import com.example.springboot.webflux.mapper.UserMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class UserHandler {
    @Resource
    private UserMapper userMapper;

    public Mono<Integer> saveUser(User user){
        return Mono.create(userSink -> userSink.success(userMapper.insert(user)));
    }
    public Flux<User> getAllUser(){
        return Flux.fromIterable(userMapper.selectByMap(null));
    }

    public Mono<User> getUserById(Integer id) {
        return Mono.justOrEmpty(userMapper.selectById(id));
    }
}
