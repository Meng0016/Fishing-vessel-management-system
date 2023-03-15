package com.mengship.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author meng
 * @Data 2022/10/13 19:56
 */
@TableName(value = "user")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Alias("用户名")   //用来解决导入导出时的别名问题
    @ApiModelProperty("用户名")
    private String username;

//    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Alias("密码")
    @ApiModelProperty("密码")
    private String password;

    @Alias("真实姓名")
    @ApiModelProperty("真实姓名")
    private String realname;

    @Alias("邮箱")
    @ApiModelProperty("邮箱")
    private String email;


    @Alias("使用状态")
    @ApiModelProperty("使用状态")
    private Integer status;

    @Alias("通过状态")
    @ApiModelProperty("通过状态")
    private Integer access;

    @Alias("登录次数")
    @ApiModelProperty("登录次数")
    private Integer loginCount;


    @Alias("最后登录时间")
    @ApiModelProperty("最后登录")
    private Integer lastVisit;


    @Alias("注册日期")
    @ApiModelProperty("创建日期")
    private LocalDateTime dateCreated;


    @Alias("Smtp")
    @ApiModelProperty("Smtp")
    private String smtp;

//    @ApiModelProperty("SSL")
//    private Boolean ssl;


    @Alias("港口")
    @ApiModelProperty("港口")
    private String port;

    @Alias("电话")
    @ApiModelProperty("电话")
    private String phone;


    @Alias("是否删除")
    @ApiModelProperty("是否删除")
    private Integer isDelete;

    @Alias("头像")
    @ApiModelProperty("头像")
    private String avatar;

    @Alias("角色")
    @ApiModelProperty("角色")
    private String role;
}
