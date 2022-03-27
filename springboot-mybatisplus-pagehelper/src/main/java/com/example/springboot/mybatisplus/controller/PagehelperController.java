package com.example.springboot.mybatisplus.controller;

import com.example.springboot.mybatisplus.entity.User;
import com.example.springboot.mybatisplus.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javafx.beans.binding.StringExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PagehelperController {

    @Autowired
    private UserMapper mapper;

    @RequestMapping("/test")
    @ApiOperation("/test分页")
    @ApiImplicitParam()
    public   PageInfo<User> Test() {
        // pagehelper
        PageInfo<User> info = PageHelper.startPage(1, 2).doSelectPageInfo(() -> mapper.selectById(1));
        return info;
//        assertThat(info.getTotal()).isEqualTo(1L);
//        List<User> list = info.getList();
//        assertThat(list).isNotEmpty();
//        assertThat(list.size()).isEqualTo(1);
    }



}
