package com.example.springboot.springdata;

import com.example.springboot.springdata.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
//
//public class EntityManagerTest {
//    private EntityManager entityManager;
//    private EntityManagerFactory entityManagerFactory;
//    private EntityTransaction transaction;
//
//    @Before
//    public void init(){
//
//        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-1");
//        entityManager = entityManagerFactory.createEntityManager();
//        transaction = entityManager.getTransaction();
//        transaction.begin();
//    }
//
//    @After
//    public void destory(){
//        transaction.commit();
//        entityManager.close();
//        entityManagerFactory.close();
//    }
//
//    //类似于在hibernate 中session 的get方法
//    //先生成数据表  === 》 之前的表可以都删除掉
//    //@Test
//    //public void testFind(){
//
//    //}
//
//    @Test
//    public void testFind(){
//        User user = entityManager.find(User.class, 1);
//        System.out.println("------------------------");
//
//        System.out.println(user);
//    }
//
//}
