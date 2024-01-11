package com.itmk.web.schedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.schedule.entity.*;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface ScheduleCourseService extends IService<ScheduleCourse> {
    int find(Long id);
    void deleteById(Long id);

    void saveSchedule(ScheduleParm scheduleParm);

    void autoSchedule(AutoScheduleParm autoScheduleParm);
    //查询排课列表
    List<ScheduleCourse> selectCourseSchedulingList(ListParm listParm);

    List<ScheduleCourse> findAll(String teacherId, String roomId);

    List<ScheduleCourse> conformity(List<ScheduleCourse> scheduleCourse);

    List<Option> getDesc();

    int count(String teacherId, String roomId);

}
