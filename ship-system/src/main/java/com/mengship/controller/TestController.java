package com.mengship.controller;

import com.mengship.entity.User;
import com.mengship.mapper.UserMapper;
import com.mengship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author meng
 * @Data 2022/10/11 21:55
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/1")
    public List<User> test1(){
        return userService.findAllUsers();
    }

}
