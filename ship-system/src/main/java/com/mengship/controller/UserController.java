package com.mengship.controller;

import com.mengship.entity.User;
import com.mengship.mapper.UserMapper;
import com.mengship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author meng
 * @Data 2022/10/13 20:18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("findAllUsers")
    public List<User> findAllUsers() {
        List<User> userList = userService.findAllUsers();
        return userList;
    }

    @PostMapping("insertUser")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @DeleteMapping("delete/{id}")
    public Integer deleteUser(@PathVariable Integer id) {
      return  userService.deleteUser(id);
    }

    @PutMapping("updateUser")
    public void updateUser(@RequestBody User user) {
         userService.updateUser(user);
    }

    @GetMapping("page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        pageNum = (pageNum - 1) * pageSize;
        List<User> users = userMapper.selectPages(pageNum, pageSize);
        Integer total = userMapper.selectTotal();
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("data",users);
        res.put("total",total);
        return res;
    }

}
