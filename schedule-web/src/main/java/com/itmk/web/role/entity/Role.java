package com.itmk.web.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
@TableName("role")
public class Role {
    @TableId(type = IdType.AUTO)
    private Long roleId;
    private String roleName;
    private String roleDesc;
}
