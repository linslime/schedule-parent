package com.itmk.web.schedule.entity;

/**
 * @Author java实战基地
 * @Version 3501754007
 */

import lombok.Data;

import java.util.List;

@Data
public class ListParm {
    private String roomId;
    //课程id列表
    private String courseId;
    //老师id列表
    private String teacherId;
    //开始日期
    private String startDate;
    //结束日期
    private String endDate;
    //上课时间
    private String beginTime;
    //下课时间
    private String endTime;
}
