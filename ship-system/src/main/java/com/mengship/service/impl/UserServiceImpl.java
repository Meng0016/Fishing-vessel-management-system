package com.mengship.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mengship.common.Constants;
import com.mengship.controller.dto.UserDto;
import com.mengship.entity.Menu;
import com.mengship.entity.User;
import com.mengship.exception.ServiceException;
import com.mengship.mapper.RoleMapper;
import com.mengship.mapper.RoleMenuMapper;
import com.mengship.mapper.UserMapper;
import com.mengship.service.IMenuService;
import com.mengship.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengship.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author meng
 * @since 2022-11-18
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Override
    public UserDto login(UserDto userDto) {
       User user = getUserInfo(userDto);
        if (user != null) {
            BeanUtil.copyProperties(user, userDto,true);  // 将查询到的用户信息复制到userDto中
            String token =TokenUtils.getToken(user.getId().toString(),user.getPassword());  // 生成token
            userDto.setToken(token);
//            String role = user.getRole(); //role_flag
//            //设置用户菜单列表
//            List<Menu> roleMenus = getRoleMenus(role);
//            userDto.setMenus(roleMenus);
//            return userDto;
            String roleFlag = user.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(roleFlag);
            userDto.setMenus(roleMenus);
            return userDto;
        } else {
            throw new  ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDto userDto) {
        User userInfo = getUserInfo(userDto);
        if (userInfo == null){
            userInfo = new User();
            BeanUtil.copyProperties(userDto,userInfo,true);
            save(userInfo);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }
        return userInfo;
    }

    private User getUserInfo(UserDto userDto){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDto.getUsername());
        wrapper.eq("password", userDto.getPassword());
        User user;
        try {
            user=getOne(wrapper);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return user;
    }

    /***
     * @Description 获取当前角色菜单
     * @Param roleFlag
     * @Return {@link List< Menu>}
     * @Author meng
     * @Date 2023/3/13 11:48
     */

    private List<Menu> getRoleMenus(String roleFlag){

        Integer roleId = roleMapper.selectByFalg(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }

        return roleMenus;
    }
}
