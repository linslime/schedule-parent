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
public class NewScheduleCourse extends ScheduleCourse{

    private int isDeleted;

}
