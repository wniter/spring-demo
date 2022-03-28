package com.example.springboot.helloworld.exception;

/**
 * 异常处理
 * 自定义异常处理
 */
public class UserNotExistException extends RuntimeException{
    private static final long serialVersionUID = -1574716826948451793L;

    private String id;
    public UserNotExistException(String id){
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
