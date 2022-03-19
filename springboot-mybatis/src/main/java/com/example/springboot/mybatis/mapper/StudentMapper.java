package com.example.springboot.mybatis.mapper;


import com.example.springboot.mybatis.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @create 2022-03-05 15:50
 */


@Mapper
@Repository
public interface StudentMapper {
    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
//    @Select("select id, name ,age from student where id = #{id}")
    Student queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 起始位置
     * @param limit  终止位置
     * @return
     */
//    @Select("select id, name, age from student limit #{offset}, #{limit}")
    List<Student> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param student
     * @re
     */
//    @Insert("insert into student(name,age) value(#{name},#{age})")
    void insert(Student student);

    /**
     * 修改数据
     *
     * @param student
     * @return
     */
//    @Update("update student set name = #{name},age = #{age} where id =#{id}")
    void update(Student student);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
//    @Delete("delete from student where id = #{id}")
    void deleteById(Integer id);
}
