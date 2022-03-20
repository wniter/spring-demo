package com.example.springboot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.mybatisplus.entity.User;
import com.example.springboot.mybatisplus.mapper.CRUDMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceCRUDImpl extends ServiceImpl<CRUDMapper, User> implements ServiceCRUD {

}
