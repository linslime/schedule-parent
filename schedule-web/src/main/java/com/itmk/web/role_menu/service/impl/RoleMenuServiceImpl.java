package com.itmk.web.role_menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.role_menu.entiy.RoleMenu;
import com.itmk.web.role_menu.entiy.SaveAssign;
import com.itmk.web.role_menu.mapper.RoleMenuMapper;
import com.itmk.web.role_menu.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Transactional
    @Override
    public void assignSve(SaveAssign saveAssign) {
        //先删除角色原来的权限
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getRoleId,saveAssign.getRoleId());
        this.baseMapper.delete(query);
        //新的插入
        this.baseMapper.assignSave(saveAssign.getRoleId(),saveAssign.getList());
    }
}
