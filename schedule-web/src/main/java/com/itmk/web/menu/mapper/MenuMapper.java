package com.itmk.web.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmk.web.menu.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface MenuMapper extends BaseMapper<Menu> {
    //根据用户id查询菜单
    List<Menu> getMenuByUserId(@Param("userId") Long userId);
    //根据角色id查询菜单
    List<Menu> getMenuByRoleId(@Param("roleId") Long roleId);
}
