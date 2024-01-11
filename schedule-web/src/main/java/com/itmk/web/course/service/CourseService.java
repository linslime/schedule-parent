package com.itmk.web.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.course.entity.Course;
import com.itmk.web.course.entity.CourseParm;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface CourseService extends IService<Course> {

    List<Course> getList(CourseParm courseParm);

    int count(CourseParm courseParm);
}
