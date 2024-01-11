package com.itmk.web.passageerFlow.entity;

import lombok.Data;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
public class ListParm {
    private Long currentPage;
    private Long pageSize;
    private String roomName;
    private String dateTime;
}
