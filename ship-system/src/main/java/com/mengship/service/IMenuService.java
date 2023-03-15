package com.mengship.service;

import com.mengship.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author meng
 * @since 2023-03-02
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
