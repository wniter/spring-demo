package com.example.springboot.mysql.vo;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @create 2022-03-20 4:56
 */
//返回前端结果Result实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private boolean flag;           //是否成功
    private Integer code;           //返回码
    private String message;         //返回消息
    private T data;                 //返回对象


}
