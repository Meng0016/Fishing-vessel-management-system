package com.mengship.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mengship.entity.Menu;
import com.mengship.mapper.MenuMapper;
import com.mengship.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author meng
 * @since 2023-03-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findMenus(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        List<Menu> menuList = list(queryWrapper);
        //找出pid为空的一级菜单
        List<Menu> parentNodes = menuList.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        //找出一级菜单的子菜单
        for (Menu  menu :parentNodes){
            //筛选pid=父级id的数据就是二级菜单
            menu.setChildren(menuList.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));

        }
        return parentNodes;
    }
}
