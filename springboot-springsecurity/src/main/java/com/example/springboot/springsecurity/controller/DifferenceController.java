package com.example.springboot.springsecurity.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Controller和@RestController的区别？
 *         官方文档：
 * @RestController is a stereotype annotation that combines @ResponseBody and @Controller.
 *         意思是：
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
 *         1)如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
 *         例如：本来应该到success.jsp页面的，则其显示success.
 *         2)如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
 *         3)如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 *
 */

/*
@RestController
@RequestMapping("/difference")
public class DifferenceController {

    // 跳转到上传文件的页面
    @RequestMapping(value = "/goToSuccessPage", method = RequestMethod.GET)
    public String goToSuccessPage() {
        // 跳转到 视图层 success.html失败
        return "success";
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Map<String, String> findAll() {
        Map<String, String> all = new HashMap<>();
        all.put("remark", "可以返回json，xml或自定义mediaType内容到页面");
        return all;
    }
}*/
