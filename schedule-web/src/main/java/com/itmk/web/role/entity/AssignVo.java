package com.itmk.web.role.entity;

/**
 * @Author java实战基地
 * @Version 3501754007
 */

import com.itmk.web.menu.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
public class AssignVo {
    //当前用户拥有的菜单
    private List<Menu> menuList = new ArrayList<>();
    //被分配的角色拥有的菜单id
    private Object[] checkList;
}