package com.example.springboot.swagger.controller;

import com.example.springboot.swagger.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @create 2022-03-06 16:36
 */

/**
 * 多个参数 ApiImplicitParams{
 * ApiImplicitParam()
 * ApiImplicitParam()
 * }
 * 单个参数
 *
 * @ApiImplicitParam： 作用在方法上，表示单独的请求参数
 * 参数：
 * 1. name ：参数名。
 * 2. value ： 参数的具体意义，作用。
 * 3. required ： 参数是否必填。
 * 4. dataType ：参数的数据类型。
 * 5. paramType ：查询参数类型，这里有几种形式：
 */
@RestController
@RequestMapping("/test")
/**
 * 类上面加上@Api的注解，说明这个类要生成api文档，并给予描述。相当于可以根据这个类作为类别的划分。
 * 在类里面的方法加上@ApiOperation 注解 用来描述这个方法(接口)是用来干嘛的；
 */
@Api("测试swagger接口")
public class TestController {

    private Map<Integer, Student> studentMap = new HashMap<>();

    @RequestMapping(path = "/getstudent", method = RequestMethod.GET)
    @ApiOperation("/根据学生id获取学生信息")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    public Student getStudent(@RequestParam("id") Integer id) {


        Student student = new Student();

        //插入多条数据
        for (int i = 0; i < 10; i++) {
            student.setId(i);
            student.setAge(12 + i);
            student.setName("helloworld" + i);
            studentMap.put(i,student);
        }
        //插入一条数据
        student.setId(11);
        student.setAge(12);
        student.setName("helloworld");
        studentMap.put(11, student);
        return studentMap.get(id);
    }
}
