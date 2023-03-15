package com.mengship.controller.dto;

import cn.hutool.core.annotation.Alias;
import com.mengship.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * @Description 接收前端登录请求数据
 * @Author meng
 * @Data 2022/11/24 16:02
 */
@Data
public class UserDto {

    @Alias("用户名")
    private String username;

    @Alias("密码")
    private String password;

    @Alias("真实姓名")
    private String realname;

    @Alias("头像")
    private String avatar;

    @Alias("令牌")
    private String token;

    @Alias("角色")
    private String role;

    @Alias("菜单")
    private List<Menu> menus;
}
