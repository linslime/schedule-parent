package com.itmk.web.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmk.web.course.entity.Course;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface CourseMapper extends BaseMapper<Course> {
    @Select("select * from course where (''=#{courseName} or course_name = #{courseName})and(''=#{courseType} or course_type = #{courseType}) and (''=#{roomName} or room_name = #{roomName})limit #{start},#{pageSize}")
    List<Course> getList(Long start,Long pageSize,String courseName,String courseType,String roomName);

    @Select("select count(All *) from course where (''=#{courseName} or course_name = #{courseName})and(''=#{courseType} or course_type = #{courseType}) and (''=#{roomName} or room_name = #{roomName})limit #{start},#{pageSize}")
    int count(Long start,Long pageSize,String courseName,String courseType,String roomName);
}
