package com.mengship.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mengship.common.Result;
import com.mengship.entity.User;
import com.mengship.mapper.ShipMapper;
import com.mengship.service.impl.ShipServiceImpl;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

import com.mengship.service.IShipService;
import com.mengship.entity.Ship;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author meng
 * @since 2022-12-27
 */
@RestController
@RequestMapping("/ship")
public class ShipController {

    @Resource
    private IShipService shipService;

    @PostMapping
    public Result save(@RequestBody Ship ship) {
        return Result.success(shipService.saveOrUpdate(ship));
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(shipService.removeById(id));
    }
    @DeleteMapping("batchDelete")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return  Result.success(shipService.removeByIds(ids));
    }
    @GetMapping
    public Result findAll() {
        return Result.success(shipService.list());
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(shipService.getById(id));
    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam (defaultValue = "") String shipName,
                           @RequestParam (defaultValue = "") String shipCode) {
        QueryWrapper<Ship> queryWrapper = new QueryWrapper<>();
        if (!"".equals(shipName)){
            queryWrapper.like("ship_name", shipName);
        }
        if (!"".equals(shipCode)){
            queryWrapper.like("ship_code", shipCode);
        }
        queryWrapper.orderByAsc("ship_id");
        return Result.success(shipService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }

    /**
     * @Description 导出
     * @Param null
     * @Return {@link null}
     * @Author meng
     * @Date 2022/11/20 16:31
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        List<Ship> list = shipService.list();
        // 通过工具类创建writer 写出到磁盘路径
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名  实体类注解Alias
        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("船只信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

}

