package com.mengship.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author meng
 * @Data 2022/10/13 19:56
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String email;
    private Integer status;
    private String phone;
    private String port;
    private String stmp;
}
