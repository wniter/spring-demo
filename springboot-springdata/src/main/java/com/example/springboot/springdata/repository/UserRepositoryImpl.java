package com.example.springboot.springdata.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl  {
    @PersistenceContext
    private EntityManager entityManager;
}
