package com.example.springboot.springdata.repository;

import com.example.springboot.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import java.util.List;

/**
 * JpaRepository<T,ID>
 * 泛型参数1，T表示的是当前需要映射的实体类类型，当前需要映射的实体。
 * 泛型参数2，ID表示需要映射的实体中的主键的类型，当前映射的实体中的OID的类型。
 * 相当于mapper
 */
public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * 查询所有
     */
    @Query(value="select * from user ")
    public List<User> findAllUser();

    /**
     * 查询单个
     */
    @Query(value = "select * from user where user.id = ?1")
    public User findUserById(Long id);


}
