package com.mengship.shipsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mengship.entity.User;
import com.mengship.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Scanner;

@SpringBootTest
class ShipSystemApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>());
        System.out.println(users);

    }




}
