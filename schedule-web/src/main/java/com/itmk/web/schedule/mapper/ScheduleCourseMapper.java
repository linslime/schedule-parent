package com.itmk.web.schedule.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.itmk.web.schedule.entity.ListParm;
import com.itmk.web.schedule.entity.Option;
import com.itmk.web.schedule.entity.ScheduleCourse;
import com.itmk.web.schedule.entity.ScheduleCourseListVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface ScheduleCourseMapper extends BaseMapper<ScheduleCourse> {
    List<ScheduleCourseListVo> getScheduleList(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);
    //排列列表
    List<ScheduleCourse> selectCourseSchedulingList(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);

//    @Select("select sc.id as id,sc.room_id as room_id, sc.course_id as course_id,sc.teacher_id as teacher_id,sc.date_time as date_time,sc.begin_time as begin_time,sc.duration as duration, sc.end_time as end_time,t.teacher_name as teacher_name,c.course_name as course_name,cr.room_name as room_name,c.course_color as course_color,cr.room_address as room_address from schedule_course sc,course c,teacher t,classroom cr where sc.course_id=c.course_id and sc.teacher_id = t.teacher_id and sc.room_id = cr.room_id and (t.teacher_id = 1 or 1 is null) and (cr.room_id = 1 or 1 is null)")
    @Select("select t.teacher_desc as teacherDesc, sc.id as id,sc.room_id as room_id,sc.teacher_id as teacher_id,sc.date_time as date_time,sc.begin_time as begin_time,sc.duration as duration, sc.end_time as end_time,t.teacher_name as teacher_name,cr.room_name as room_name,t.color as color,cr.room_address as room_address from schedule_course sc,teacher t, classroom cr where sc.teacher_id = t.teacher_id and sc.room_id = cr.room_id  and (t.teacher_desc = #{teacherId} or t.teacher_skill like #{ teacherId} or t.teacher_name = #{teacherId} or #{teacherId} = '') and (cr.room_id = #{roomId} or #{roomId} = '')")
    List<ScheduleCourse> findAll(String teacherId, String roomId);

    @Delete("delete from schedule_course where #{id}=id")
    void deleteById(Long id);

    @Select("select id from schedule_course where #{id} = id")
    int find(Long id);

    @Select("select DISTINCT teacher_desc as value ,teacher_desc as label from teacher")
    List<Option> getDesc();

    @Select("select count(distinct t.teacher_name) from schedule_course sc,teacher t, classroom cr where sc.teacher_id = t.teacher_id and sc.room_id = cr.room_id  and (t.teacher_desc = #{teacherId} or t.teacher_skill = #{ teacherId} or t.teacher_name = #{teacherId} or #{teacherId} = '') and (cr.room_id = #{roomId} or #{roomId} = '')")
    int count(String teacherId, String roomId);
}
