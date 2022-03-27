package com.example.springboot.mybatisplus.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "User",description = "学生类")
public class User {

    private Long id;

    private String name;
}