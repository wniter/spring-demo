package com.example.springboot.helloworld.conrtroller;

import com.example.springboot.helloworld.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class ControllerExceptionHandler {

    /**
     * 其中注解@ExceptionHandler指定了要处理的异常类型，注解@ResponseStatus指定异常处理方法返回的HTTP状态码为HttpStatus.INTERNAL_SERVER_ERROR，
     * 即500。HttpStatus是一个spring自带的枚举类型，封装了常见的HTTP状态码及描述：
     * @param e
     * @return
     */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistsException(UserNotExistException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("message", e.getMessage());
        return map;
    }

    @GetMapping("/{id:\\d+}")
    public void get(@PathVariable String id) {
//        throw new RuntimeException("user not exist");
        throw new UserNotExistException(id);
    }
}
