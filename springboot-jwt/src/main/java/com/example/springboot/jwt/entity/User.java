package com.example.springboot.jwt.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class User {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private String name;
    private String password;
}
