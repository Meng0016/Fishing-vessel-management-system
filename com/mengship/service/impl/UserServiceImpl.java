package com.mengship.service.impl;

import com.mengship.entity.User;
import com.mengship.mapper.UserMapper;
import com.mengship.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author meng
 * @since 2022-11-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
