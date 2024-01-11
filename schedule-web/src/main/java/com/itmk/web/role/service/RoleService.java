package com.itmk.web.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.role.entity.AssignParm;
import com.itmk.web.role.entity.AssignVo;
import com.itmk.web.role.entity.ListParm;
import com.itmk.web.role.entity.Role;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface RoleService extends IService<Role> {
    IPage<Role> getList(ListParm listParm);
    //角色权限的回显
    AssignVo getAssignShow(AssignParm parm);
}
