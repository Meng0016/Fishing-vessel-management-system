package com.mengship.service;

import com.mengship.controller.dto.UserDto;
import com.mengship.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author meng
 * @since 2022-11-18
 */
public interface IUserService extends IService<User> {

    UserDto login(UserDto userDto); // 登录

    User register(UserDto userDto);
}
