package com.itmk.web.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.user.entity.PageParm;
import com.itmk.web.user.entity.User;
import com.itmk.web.user.mapper.UserMapper;
import com.itmk.web.user.service.UserService;
import com.itmk.web.user_role.entity.UserRole;
import com.itmk.web.user_role.service.UserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleService userRoleService;
    @Override
    @Transactional
    public void addUser(User user) {
        //新增用户
        int insert = this.baseMapper.insert(user);
        //设置用户的角色
        if(insert > 0){
            UserRole userRole = new UserRole();
            userRole.setRoleId(user.getRoleId());
            userRole.setUserId(user.getUserId());
            userRoleService.save(userRole);
        }
    }

    @Override
    @Transactional
    public void editUser(User user) {
        int i = this.baseMapper.updateById(user);
        //先删除再插入
        if(i > 0){
            QueryWrapper<UserRole> query = new QueryWrapper<>();
            query.lambda().eq(UserRole::getUserId,user.getUserId());
            userRoleService.remove(query);
            UserRole userRole = new UserRole();
            userRole.setRoleId(user.getRoleId());
            userRole.setUserId(user.getUserId());
            userRoleService.save(userRole);
        }
    }

    @Override
    public void deletUser(Long userId) {
        int i = this.baseMapper.deleteById(userId);
        //删除角色
        if(i > 0){
            QueryWrapper<UserRole> query = new QueryWrapper<>();
            query.lambda().eq(UserRole::getUserId,userId);
            userRoleService.remove(query);
        }
    }

    @Override
    public IPage<User> getUserList(PageParm parm) {
        //构造分页对象
        IPage page = new Page(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<User> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getPhone())){
            query.lambda().like(User::getPhone,parm.getPhone());
        }
        if(StringUtils.isNotEmpty(parm.getName())){
            query.lambda().like(User::getName,parm.getName());
        }
        return this.baseMapper.selectPage(page,query);
    }
}
