package com.example.springboot.springdata.repository;

import com.example.springboot.springdata.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository<T,ID>
 * 泛型参数1，T表示的是当前需要映射的实体类类型，当前需要映射的实体。
 * 泛型参数2，ID表示需要映射的实体中的主键的类型，当前映射的实体中的OID的类型。
 * 相当于mapper
 */
public interface UserRepository extends JpaRepository<User, Long> {

    //  //方法名称必须要遵循驼峰式命名规则，findBy（关键字）+属性名称（首字母大写）+查询条件（首字母大写）
//    /**
//     * 查询所有
//     */
//    @Query(value="select * from user ")
//    public List<User> findAllUser();
//
//
//    /**
//     * 查询单个
//     */
//    @Query(value = "select * from user where user.id = ?1")
//    public User findUserById(Long id);
//

    @NonNull
    Optional<User> findByName(@NonNull String name);

    /**
     * UserRepository 只需继承 JpaRepository，便可自动生成基本的 CRUD 方法，如 findById()，save() 等。
     * 上述代码中，我们还自定义了一个方法 findByName()，JpaRepository 可根据方法名称自动实现对应的逻辑。
     */
}
