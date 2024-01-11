package com.itmk.web.role_menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmk.web.role_menu.entiy.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    //保存角色的权限
    void assignSave(@Param("roleId") Long roleId, @Param("menuList") List<Long> menuList);
}
