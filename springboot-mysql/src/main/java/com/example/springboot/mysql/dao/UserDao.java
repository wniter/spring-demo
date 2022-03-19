package com.example.springboot.mysql.dao;

import com.example.springboot.mysql.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @create 2022-03-20 2:54
 */
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //添加
    public int add(User user) {
        String sql = "INSERT INTO USER (NAME, age,email) VALUES(?,?,?)";
        return jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getEmail());
    }

    //修改
    public int update(User user) {
        String sql = "UPDATE USER SET NAME =?,age = ?,email = ? where id = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getEmail(), user.getId());
    }

    //删除
    public int delete(Integer id) {
        String sql = "DELETE from user from id = ?";
        return jdbcTemplate.update(sql, id);
    }

    //根据id查找
    public User getUserById(Integer id) {
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    //全部查找
    public List<User> getAllUser() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }


}
