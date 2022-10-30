package com.mengship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengship.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
     List<User> findAllUsers();

     void updateUser(User user);

     List<User> selectPages(Integer pageNum, Integer pageSize);

     Integer selectTotal();
}
