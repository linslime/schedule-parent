package com.itmk.web.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.menu.entity.MakeTree;
import com.itmk.web.menu.entity.Menu;
import com.itmk.web.menu.mapper.MenuMapper;
import com.itmk.web.menu.service.MenuService;
import com.itmk.web.role_menu.entiy.RoleMenu;
import com.itmk.web.role_menu.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    @Transactional
    public void deleteMenu(Long menuId) {
        //删除菜单
        int i = this.baseMapper.deleteById(menuId);
        if(i>0){
            //删除菜单对应的关联
            QueryWrapper<RoleMenu> query = new QueryWrapper<>();
            query.lambda().eq(RoleMenu::getMenuId,menuId);
            roleMenuService.remove(query);
        }
    }

    @Override
    public List<Menu> getList() {
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().orderByAsc(Menu::getOrderNum);
        //获取菜单
        List<Menu> menuList = this.baseMapper.selectList(query);
        //组装树数据
        List<Menu> menus = MakeTree.makeMenuTree(menuList, 0L);

        return menus;
    }

    @Override
    public List<Menu> parentList() {
        //只获取目录和菜单
        String[] types = {"0","1"};
        //查询条件
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().in(Menu::getType, Arrays.asList(types)).orderByAsc(Menu::getOrderNum);
        List<Menu> menus = this.baseMapper.selectList(query);
        //构造顶级菜单
        Menu menu = new Menu();
        menu.setMenuId(0L);
        menu.setParentId(-1L);
        menu.setTitle("顶级菜单");
        menus.add(menu);
        //构造树形数据
        List<Menu> menuTree = MakeTree.makeMenuTree(menus, -1L);
        return menuTree;
    }

    @Override
    public List<Menu> getMenuByUserId(Long userId) {
        return this.baseMapper.getMenuByUserId(userId);
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        return this.baseMapper.getMenuByRoleId(roleId);
    }
}
