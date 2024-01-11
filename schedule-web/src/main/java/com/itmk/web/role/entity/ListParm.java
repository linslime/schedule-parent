package com.itmk.web.role.entity;

import lombok.Data;

/**
 * @Author java实战基地
 * @Version 3501754007
 */

@Data
public class ListParm {
    private int pageSize;
    private int currentPage;
    private String roleName;
}
