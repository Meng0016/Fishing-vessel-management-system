package com.mengship.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
  @ApiModel(value = "Role对象", description = "")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private String description;

    @ApiModelProperty("唯一标识")
    private String flag;

    @Alias("是否删除")
    @ApiModelProperty("是否删除")
    private Integer isDelete;


}
