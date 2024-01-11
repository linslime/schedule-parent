package com.itmk.web.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.menu.entity.Menu;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface MenuService extends IService<Menu> {
    //删除
    void deleteMenu(Long menuId);
    //列表
    List<Menu> getList();
    //上级树数据
    List<Menu> parentList();
     //根据用户id查询菜单
    List<Menu> getMenuByUserId(Long userId);
    //根据角色id查询菜单
    List<Menu> getMenuByRoleId(Long roleId);
}
