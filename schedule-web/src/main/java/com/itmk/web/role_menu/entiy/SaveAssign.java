package com.itmk.web.role_menu.entiy;


import lombok.Data;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
public class SaveAssign {
    private Long roleId;
    private List<Long> list;
}