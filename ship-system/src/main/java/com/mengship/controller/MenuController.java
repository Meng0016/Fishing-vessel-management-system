package com.mengship.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mengship.common.Constants;
import com.mengship.common.Result;
import com.mengship.entity.Dict;
import com.mengship.mapper.DictMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import com.mengship.service.IMenuService;
import com.mengship.entity.Menu;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author meng
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Resource
    private DictMapper dictMapper;

    @PostMapping
    public Result save(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
    }
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(menuService.removeById(id));
    }
    @DeleteMapping("batchDelete")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return  Result.success(menuService.removeByIds(ids));
    }
    @GetMapping("/all")
    public Result findAll(@RequestParam(defaultValue = "") String name) {
        return Result.success( menuService.findMenus(name));
    }

    @GetMapping("/ids")
    public Result findAllIds() {
        return Result.success(menuService.list().stream().map(Menu::getId));
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.list());
    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)){
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }

    @GetMapping("/icons")
    public Result getIcons(){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(queryWrapper));
    }

}

