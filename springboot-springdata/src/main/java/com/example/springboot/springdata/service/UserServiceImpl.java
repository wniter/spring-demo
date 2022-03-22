package com.example.springboot.springdata.service;

import com.example.springboot.springdata.entity.User;

import com.example.springboot.springdata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //
    @Autowired
    UserRepository userRepository;


//    public List<User> findAllUser () {
//        return userRepository.findAllUser();
//    }
//
//    public User findUserById(Long id) {
//        return userRepository.findUserById(id);
//    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

}
