package com.itmk.web.teacher.entity;

import lombok.Data;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
public class TeacherParm {
    private Long currentPage;
    private Long pageSize;
    private String teacherName;
    private String roomId;
}
