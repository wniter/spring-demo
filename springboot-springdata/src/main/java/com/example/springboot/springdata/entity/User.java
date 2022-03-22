package com.example.springboot.springdata.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity //为什么不能用@Compont
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue //主键的生成策略，例：@GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(name = "name")
  private String name;
  @Column(name = "age")
  private long age;
  @Column(name = "email")
  private String email;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
/**
 @Data：lombok 注解，可自动生成 get()、set()、toString() 等方法；
 @Entity：JPA 注解，声明该类为一个实体类，必须与 @Id 搭配使用；
 @Table：JPA 注解，表示该实体类会映射到数据库的 user 表（name 指定表名）；
 @Id：JPA 注解，声明主键；
 @GeneratedValue：JPA 注解，表示主键的生成策略，IDENTITY 表示使用自增 id；
 @Column：JPA 注解，声明实体对象的属性映射到数据表中的哪一个字段，name 指定字段名，columnDefinition 指定字段的定义。
 @MappedSuperclass：JPA 注解，应用于实体类的父类，该注解作用的类不会映射到数据表，但其属性都将映射到子类的数据表。
 @PrePersist：被 @PrePersist 修饰的方法在将实体持久化到数据库之前被调用；
 @PreUpdate：被 @PreUpdate 修饰的方法在实体的某个属性发生变动时被调用，如更新实体的 update_time；
 @PreRemove：被 @PreRemove 修饰的方法在实体从数据库删除前被调用。
 */