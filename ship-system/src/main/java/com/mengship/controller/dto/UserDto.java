package com.mengship.controller.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

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
}
