package com.mengship.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author meng
 * @Data 2023/3/12 17:51
 */

@TableName("dict")
@Data
public class Dict {
    private String name;
    private String value;
    private String type;
}
