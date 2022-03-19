package com.example.springboot.mybatis.controller;


import com.example.springboot.mybatis.entity.Student;
import com.example.springboot.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @create 2022-03-05 16:31
 */
@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    StudentService studentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id
     * @return
     */
    @GetMapping("/queryById/{id}")
    public Student queryById(@RequestParam("id") Integer id) {
        return studentService.queryById(id);
    }

    @GetMapping("/queryAllByLimit/{off}-{limit}")
    public List<Student> queryAllByLimit(@RequestParam("off") Integer off,
                                         @RequestParam("limit") Integer limit) {
        return studentService.queryAllByLimit(off, limit);
    }
    //insert、delete、update操作默认返回一个int类型的整数，将增删改的接口改成int或者void即可。
    @GetMapping("/insert")
    public String insert(Student student) {
        student.setAge(111);
        student.setName("222");
        student.setId(2);
        studentService.insert(student);
        return "ok";
    }

    @GetMapping("/update")
    public String update(Student student) {
        student.setAge(111);
        student.setName("222");
        student.setId(2);
        System.out.println(student.toString());
        studentService.update(student);
        return "ok";

    }

    @GetMapping("/deleteById")
    public void deleteById(Integer id) {
         studentService.deleteById(id) ;
    }


}
