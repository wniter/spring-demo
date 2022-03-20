package com.example.springboot.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @create 2022-03-20 21:11
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Strudent {
    private Integer id ;
    private String name;
    private Integer age;
}
