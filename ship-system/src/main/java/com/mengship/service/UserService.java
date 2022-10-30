package com.mengship.service;

import com.mengship.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    int insertUser(User user);

    int deleteUser(Integer id);

    void updateUser(User user);
}
