package com.example.springboot.springdata.controller;

import com.example.springboot.springdata.entity.User;
import com.example.springboot.springdata.repository.UserRepository;
import com.example.springboot.springdata.service.UserService;
import com.example.springboot.springdata.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    //
//    @ResponseBody
//    @RequestMapping("/list")
//    public Result<List<User>> findAllUser() {
//        List<User> users = userService.findAllUser();
//        return new Result<>(users);
//
//    }
//    @ResponseBody
//    @RequestMapping("/list/{id}")
//    public Result<User> findAllUser(Long id) {
//        User user = userService.findUserById(id);
//        return new Result<>(user);
//    }
    @RequestMapping(path = "addUser", method = RequestMethod.POST)
    @ResponseBody
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping(path = "deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(@RequestBody User user) {
        userRepository.delete(user);
    }

    @RequestMapping(path = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User queryUserById(@PathVariable("id") long id) {
        return userRepository.findById(id).orElse(null);
    }

    @RequestMapping(path = "/getByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public User queryUserByName(@PathVariable("name") String name) {
        Optional<User> optional = userRepository.findByName(name);
        return optional.orElse(null);
    }

}
