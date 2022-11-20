package com.mengship.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ApiModelProperty("用户名")
    private String username;

    @JsonIgnore
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("真实姓名")
    private String realname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("使用状态")
    private Integer status;

    @ApiModelProperty("通过状态")
    private Integer access;

    @ApiModelProperty("登录次数")
    private Integer loginCount;

    @ApiModelProperty("最后登录")
    private Integer lastVisit;

    @ApiModelProperty("创建日期")
    private LocalDateTime dateCreated;

    @ApiModelProperty("Smtp")
    private String smtp;

//    @ApiModelProperty("SSL")
//    private Boolean ssl;

    @ApiModelProperty("港口")
    private String port;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("是否删除")
    private Integer isDelete;

}
