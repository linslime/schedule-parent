package com.itmk.web.passageerFlow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
@TableName("passager_flow")
public class PassagerFlow {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String roomName;
    //日期
    private String dateTime;
    //上课时间
    private String beginTime;
    //下课时间
    private String endTime;

    private float passagerFlow;

    private int passagerNumber;
}
