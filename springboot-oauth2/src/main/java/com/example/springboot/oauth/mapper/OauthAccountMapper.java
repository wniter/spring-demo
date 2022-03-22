package com.example.springboot.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.oauth.entity.OauthAccount;

import org.apache.ibatis.annotations.Param;


public interface OauthAccountMapper extends BaseMapper<OauthAccount> {

    /**
     * 获取客户端用户信息
     *
     * @param clientId 客户端Id
     * @param username 用户名
     * @return 用户对象
     */
    OauthAccount loadUserByUsername(@Param("clientId") String clientId, @Param("username") String username);

}
