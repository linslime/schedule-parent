package com.itmk.web.menu.entity;

import com.itmk.web.login.entity.RouterVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public class MakeTree {
    public static List<Menu> makeMenuTree(List<Menu> menuList, Long pid) {
        //存放组装的树数据
        List<Menu> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId() == pid)
                .forEach(dom -> {
                    Menu menu = new Menu();
                    BeanUtils.copyProperties(dom, menu);
                    //获取下级
                    List<Menu> sysMenus = makeMenuTree(menuList, dom.getMenuId());
                    //设置下级
                    menu.setChildren(sysMenus);
                    list.add(menu);
                });
        return list;
    }

    //构造路由数据
    public static List<RouterVO> makeRouter(List<Menu> menuList, Long pid) {
        //存放构造好的路由数据
        List<RouterVO> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId() == pid)
                .forEach(item -> {
                    RouterVO routerVO = new RouterVO();
                    routerVO.setName(item.getName());
                    routerVO.setPath(item.getPath());
                    //设置Component
                    if (item.getParentId() == 0L) {
                        routerVO.setComponent("Layout");
                    } else {
                        routerVO.setComponent(item.getUrl());
                    }
                    //设置路由的meta
                    routerVO.setMeta(routerVO.new Meta(
                            item.getTitle(),
                            item.getIcon(),
                            item.getCode().split(",")
                    ));
                    //设置children
                    List<RouterVO> children = makeRouter(menuList, item.getMenuId());
                    routerVO.setChildren(children);
                    list.add(routerVO);
                });
        return list;
    }
}
