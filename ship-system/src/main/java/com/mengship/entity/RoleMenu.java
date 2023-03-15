package com.mengship.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author meng
 * @Data 2023/3/13 9:47
 */
@TableName("role_menu")
@Data
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;
}
