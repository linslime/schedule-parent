package com.itmk.web.schedule.entity;

/**
 * @Author java实战基地
 * @Version 3501754007
 */

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * 接收前端排课参数
 */
@Data
public class ScheduleParm {
    //教室id
    private Long roomId;
    //教师id
    private Long teacherId;
    //上课时间
    private LocalTime beginTime;
    //下课时间
    private LocalTime endTime;
    //开课日期
    private LocalDate startDate;
    //结课日期
    private LocalDate endDate;
    //周几上课列表
    private List<Integer> weeks;
    private Long duration;
}
