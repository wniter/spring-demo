package com.example.springboot.mybatis.service;

import com.example.springboot.mybatis.entity.Student;
import com.example.springboot.mybatis.mapper.StudentMapper;
import org.apache.ibatis.executor.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2022-03-05 15:55
 */
@Service
public class StudentServiceImpl implements StudentService {
    public StudentServiceImpl () {

    }

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student queryById(Integer id) {
        return studentMapper.queryById(id);
    }

    @Override
    public List<Student> queryAllByLimit(int offset, int limit) {
        return studentMapper.queryAllByLimit(offset,limit);
    }

    @Override
    public void insert(Student student) {
         studentMapper.insert(student);
    }


    @Override
    public void update(Student student) {
         studentMapper.update(student);
    }

    @Override
    public void deleteById(Integer id) {
         studentMapper.deleteById(id);
    }


}
