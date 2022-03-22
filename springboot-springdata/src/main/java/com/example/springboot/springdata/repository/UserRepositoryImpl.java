package com.example.springboot.springdata.repository;

import com.example.springboot.springdata.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

//多余
//@Repository
//@Transactional
//public class UserRepositoryImpl  {
//    //@PersistenceContext，存放unitName指向的DataBase对应的EntityBean实例集合，以及对这些实例进行生命周期管理
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    public Long insert(final User user)
//    {
//        entityManager.persist(user);
//        return user.getId();
//    }
//
//    public void delete(final Long userId)
//    {
//        Query query = entityManager.createQuery("DELETE FROM User  o WHERE o.id = ?1");
//        query.setParameter(1, userId);
//        query.executeUpdate();
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<User> selectAll()
//    {
//        return (List<User>) entityManager.createQuery("SELECT o FROM User o").getResultList();
//    }
//
//    @SuppressWarnings("unchecked")
//    public User selectOne(final Long userId)
//    {
//        Query query = entityManager.createQuery("SELECT o FROM User o WHERE o.id = ?1");
//        query.setParameter(1, userId);
//        return (User) query.getSingleResult();
//    }
//}

/**
 @PersistenceContext是jpa专有的注解，而@Autowired是spring自带的注释

 EntityManager不是线程安全的，当多个请求进来的时候，spring会创建多个线程，@PersistenceContext就是用来为每个线程创建一个EntityManager的，
 而@Autowired只创建了一个，为所有线程共用，有可能报错

 在使用EntityManager的时，请采用@PersistenceContext进行注解，而不要使用@Autowired

 */