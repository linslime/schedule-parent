package com.itmk.web.role_menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.role_menu.entiy.RoleMenu;
import com.itmk.web.role_menu.entiy.SaveAssign;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface RoleMenuService extends IService<RoleMenu> {
    void assignSve(SaveAssign saveAssign);
}
