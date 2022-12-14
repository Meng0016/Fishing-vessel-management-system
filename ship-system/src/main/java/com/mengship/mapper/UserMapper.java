package com.mengship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengship.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author meng
 * @since 2022-11-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
