package com.mengship.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author meng
 * @since 2023-03-02
 */
@Getter
@Setter
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private String path;

    private String icon;

    private String description;

    private Integer pid;

    @TableField(exist = false)
    private List<Menu> children;

    @Alias("是否删除")
    @ApiModelProperty("是否删除")
    private Integer isDelete;
}
