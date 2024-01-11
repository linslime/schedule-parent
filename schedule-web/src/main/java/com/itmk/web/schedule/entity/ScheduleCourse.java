package com.itmk.web.schedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
@TableName("schedule_course")
public class ScheduleCourse {
    @TableId(type = IdType.AUTO)
    private Long id;
    //教室id
    private Long roomId;
    //教师id
    private Long teacherId;
    //日期
    private LocalDate dateTime;
    //上课时间
    private LocalTime beginTime;
    //课程时长
    private Long duration;
    //下课时间
    private LocalTime endTime;
    @TableField(exist = false)
    private String teacherName;
    @TableField(exist = false)
    private String roomName;
    @TableField(exist = false)
    private String color;

    private String teacherDesc;
}
