package com.itmk.web.teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
@TableName("teacher")
public class Teacher {
    @TableId(type = IdType.AUTO)
    private Long teacherId;
    private String teacherName;
    //教师编号
    private String teacherNum;
    //描述
    private String teacherDesc;
    private String teacherStores;
    private String teacherEmail;
    private String teacherSkill;
}
