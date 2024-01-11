package com.itmk.web.course.entity;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
import lombok.Data;

@Data
public class CourseParm {
    private Long currentPage;
    private Long pageSize;
    private String courseName;
    private String courseType;
    private String roomName;
}
