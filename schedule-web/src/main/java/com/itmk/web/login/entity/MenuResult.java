package com.itmk.web.login.entity;

/**
 * @Author java实战基地
 * @Version 3501754007
 */

import lombok.Data;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
public class MenuResult {
    //菜单数据
    private List<RouterVO> menuList;
    //权限字段
    private Object[] authList;
}
