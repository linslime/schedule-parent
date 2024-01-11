package com.itmk.web.role_menu.entiy;

/**
 * @Author java实战基地
 * @Version 3501754007
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
@TableName("role_menu")
public class RoleMenu {
    @TableId(type = IdType.AUTO)
    private Long roleMenuId;
    private Long roleId;
    private Long menuId;
}