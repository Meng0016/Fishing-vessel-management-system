package com.mengship.controller;

import cn.hutool.core.collection.CollUtil;
import com.mengship.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author meng
 * @Data 2023/2/28 16:58
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @GetMapping("/example")
    public Result get(){
        Map<String,Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon","Tue","Wed","Thu","Fri","Sat","Sun"));
        map.put("y", CollUtil.newArrayList(123,15,1816,46,511,666,77));

        return Result.success(map);
    }
}
