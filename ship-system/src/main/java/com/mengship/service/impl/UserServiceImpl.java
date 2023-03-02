package com.mengship.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mengship.common.Constants;
import com.mengship.controller.dto.UserDto;
import com.mengship.entity.User;
import com.mengship.exception.ServiceException;
import com.mengship.mapper.UserMapper;
import com.mengship.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengship.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author meng
 * @since 2022-11-18
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDto login(UserDto userDto) {
       User user = getUserInfo(userDto);
        if (user != null) {
            BeanUtil.copyProperties(user, userDto);  // 将查询到的用户信息复制到userDto中
            String token =TokenUtils.getToken(user.getId().toString(),user.getPassword());  // 生成token
            userDto.setToken(token);
            return userDto;
        } else {
            throw new  ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDto userDto) {
        User userInfo = getUserInfo(userDto);
        if (userInfo == null){
            userInfo = new User();
            BeanUtil.copyProperties(userDto,userInfo,true);
            save(userInfo);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }
        return userInfo;
    }

    private User getUserInfo(UserDto userDto){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDto.getUsername());
        wrapper.eq("password", userDto.getPassword());
        User user;
        try {
            user=getOne(wrapper);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return user;
    }
}
