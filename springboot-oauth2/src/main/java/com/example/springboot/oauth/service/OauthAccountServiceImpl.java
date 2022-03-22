package com.example.springboot.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.oauth.entity.OauthAccount;
import com.example.springboot.oauth.mapper.OauthAccountMapper;
import org.springframework.stereotype.Service;

@Service
public class OauthAccountServiceImpl extends ServiceImpl<OauthAccountMapper,OauthAccount> implements OauthAccountService{
}
