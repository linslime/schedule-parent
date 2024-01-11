package com.itmk.web.user_role.entity;

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
@TableName("user_role")
public class UserRole {
    @TableId(type = IdType.AUTO)
    private Long userRoleId;
    private Long userId;
    private Long roleId;
}