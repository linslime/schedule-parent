package com.itmk.web.user.entity;

import lombok.Data;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
public class PageParm {
    private Long currentPage;
    private Long pageSize;
    private String phone;
    private String name;
}