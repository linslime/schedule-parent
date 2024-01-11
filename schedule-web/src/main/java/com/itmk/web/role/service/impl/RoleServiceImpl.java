package com.itmk.web.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.menu.entity.MakeTree;
import com.itmk.web.menu.entity.Menu;
import com.itmk.web.menu.service.MenuService;
import com.itmk.web.role.entity.AssignParm;
import com.itmk.web.role.entity.AssignVo;
import com.itmk.web.role.entity.ListParm;
import com.itmk.web.role.entity.Role;
import com.itmk.web.role.mapper.RoleMapper;
import com.itmk.web.role.service.RoleService;
import com.itmk.web.user.entity.User;
import com.itmk.web.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Override
    public IPage<Role> getList(ListParm listParm) {
        //构造分页对象
        IPage<Role> page = new Page<>();
        page.setSize(listParm.getPageSize());
        page.setCurrent(listParm.getCurrentPage());
        //构造查询条件
        QueryWrapper<Role> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(listParm.getRoleName())){
            query.lambda().like(Role::getRoleName,listParm.getRoleName());
        }
        return this.baseMapper.selectPage(page,query);
    }

    @Override
    public AssignVo getAssignShow(AssignParm parm) {
        //查询当前用户信息
        User user = userService.getById(parm.getUserId());
        //查询用户的菜单
        List<Menu> list = null;
        if(user.getIsAdmin().equals("1")){ //管理员，直接查询所有的菜单
            QueryWrapper<Menu> query = new QueryWrapper<>();
            query.lambda().orderByAsc(Menu::getOrderNum);
            list = menuService.list(query);
        }else{
            list = menuService.getMenuByUserId(parm.getUserId());
        }
        //组装菜单数据
        List<Menu> menuList = MakeTree.makeMenuTree(list, 0L);
        //查询角色对应的菜单
        List<Long> ids = new ArrayList<>();
        List<Menu> roleList = menuService.getMenuByRoleId(parm.getRoleId());
        Optional.ofNullable(roleList).orElse(new ArrayList<>())
                .stream()
                .filter(item ->item != null)
                .forEach(item ->{
                    ids.add(item.getMenuId());
                });
        AssignVo vo = new AssignVo();
        vo.setCheckList(ids.toArray());
        vo.setMenuList(menuList);
        return vo;
    }
}
