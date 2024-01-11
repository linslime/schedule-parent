package com.itmk.web.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.course.entity.Course;
import com.itmk.web.course.entity.CourseParm;
import com.itmk.web.course.mapper.CourseMapper;
import com.itmk.web.course.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Resource
    CourseMapper courseMapper;

    @Override
    public List<Course> getList(CourseParm courseParm){
        return courseMapper.getList((courseParm.getCurrentPage() - 1) * courseParm.getPageSize(),courseParm.getPageSize(),courseParm.getCourseName(),courseParm.getCourseType(),courseParm.getRoomName());
    }

    @Override
    public int count(CourseParm courseParm){
        return courseMapper.count((courseParm.getCurrentPage() - 1) * courseParm.getPageSize(),courseParm.getPageSize(),courseParm.getCourseName(),courseParm.getCourseType(),courseParm.getRoomName());
    }
}
