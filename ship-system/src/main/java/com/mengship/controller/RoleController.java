package com.mengship.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mengship.common.Result;
import com.mengship.entity.Menu;
import com.mengship.mapper.RoleMenuMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.mengship.service.IRoleService;
import com.mengship.entity.Role;

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
@RequestMapping("/role")
public class RoleController {


    @Resource
    private IRoleService roleService;

    @PostMapping
    public Result save(@RequestBody Role role) {
        return Result.success(roleService.saveOrUpdate(role));
    }
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(roleService.removeById(id));
    }
    @DeleteMapping("batchDelete")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return  Result.success(roleService.removeByIds(ids));
    }
    @GetMapping
    public Result findAll() {
        return Result.success(roleService.list());
    }


    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(roleService.list());
    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)){
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }

    /***
     * @Description   绑定角色菜单关系
     * @Param roleId
     * @Param menuIds
     * @Return {@link Result}
     * @Author meng
     * @Date 2023/3/13 9:50
     */

    @PostMapping("/roleMenu/{roleId}")
    public Result RoleMenu(@PathVariable Integer roleId,@RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId,menuIds);
        return Result.success();
    }

    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId) {
        return Result.success(roleService.getRoleMenu(roleId));
    }
}

