package com.itmk.web.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.user.entity.PageParm;
import com.itmk.web.user.entity.User;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface UserService extends IService<User>{
    //新增
    void addUser(User user);
    //编辑
    void editUser(User user);
    //删除
    void deletUser(Long userId);
    //列表
    IPage<User> getUserList(PageParm parm);
}
