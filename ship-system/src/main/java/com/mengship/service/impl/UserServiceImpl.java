package com.mengship.service.impl;

import com.mengship.entity.User;
import com.mengship.mapper.UserMapper;
import com.mengship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author meng
 * @Data 2022/10/13 20:14
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;

    @Override
    public List<User> findAllUsers() {
        return usermapper.findAllUsers();
    }

    @Override
    public int insertUser(User user) {
        return usermapper.insert(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return usermapper.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        usermapper.updateUser(user);
    }
}
