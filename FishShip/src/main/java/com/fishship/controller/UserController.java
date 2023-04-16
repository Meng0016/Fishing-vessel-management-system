package com.fishship.controller;


import com.fishship.service.UserService;
import com.fishship.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author meng
 * @since 2023-04-16
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /***
     * @Description 查询所有用户信息
     * @Param null
     * @Return {@link null}
     * @Author meng
     * @Date 2023/4/16 11:38
     */
    @GetMapping("/listAll")
    public Result listAll(){
        return Result.ok(userService.list());
    }

}

