package com.itmk.web.schedule.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoScheduleParm {
    //教室id
    private Long roomId;
    //开课日期
    private LocalDate startDate;
    //结课日期
    private LocalDate endDate;
}
