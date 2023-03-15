package com.mengship.mapper;

import com.mengship.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author meng
 * @since 2023-03-02
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select id from role where flag = #{flag} ")
    Integer selectByFalg(@Param("flag") String role);
}
