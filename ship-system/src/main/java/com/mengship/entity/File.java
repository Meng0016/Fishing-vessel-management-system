package com.mengship.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author meng
 * @Data 2023/2/23 11:12
 */
@Data
@TableName("file")
public class File {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private Long size;
    private String url;

    @Alias("是否删除")
    @ApiModelProperty("逻辑删除")
    private Boolean isDelete;

    private Boolean enable;
}
