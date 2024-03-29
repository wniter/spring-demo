package com.example.springboot.mybatisplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 使用mybatis-plus
 * 第一种方式直接extends  BaseMapper<T>
 * serivce层不做处理
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
