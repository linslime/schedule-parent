package com.itmk.web.schedule.controller;

import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.classroom.entity.ClassRoom;
import com.itmk.web.classroom.service.ClassRoomService;
import com.itmk.web.course.entity.Course;
import com.itmk.web.course.service.CourseService;
import com.itmk.web.schedule.entity.*;
import com.itmk.web.schedule.service.ScheduleCourseService;
import com.itmk.web.teacher.entity.Teacher;
import com.itmk.web.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/schedule")
public class ScheduleCourseController {
    @Autowired
    private ScheduleCourseService scheduleCourseService;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    //教室列表
    @GetMapping("/getRoomList")
    public ResultVo getRoomList(){
        //查询list
        List<ClassRoom> list = classRoomService.list();
        //组装前端需要的数据格式
        List<SelectOption> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item->{
                    SelectOption option = new SelectOption();
                    option.setValue(item.getRoomId());
                    option.setLabel(item.getRoomName());
                    selectOptions.add(option);
        });
        System.out.println("ttt");
        System.out.println(selectOptions);
        System.out.println("ttt");
        return ResultUtils.success("查询成功",selectOptions);
    }

    //教师
    @GetMapping("/getTeacher")
    public ResultVo getTeacher(){
        List<Teacher> list = teacherService.list();
        List<SelectOption> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item->{
                    SelectOption option = new SelectOption();
                    option.setValue(item.getTeacherId());
                    option.setLabel(item.getTeacherName());
                    selectOptions.add(option);
        });
        return ResultUtils.success("查询成功",selectOptions);
    }

    //课程
    @GetMapping("/getCourseList")
    public ResultVo getCourseList(){
        List<Course> list = courseService.list();
        List<SelectOption> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item->{
                    SelectOption option = new SelectOption();
                    option.setValue(item.getCourseId());
                    option.setLabel(item.getCourseName());
                    selectOptions.add(option);
        });
        return ResultUtils.success("查询成功",selectOptions);
    }
    @GetMapping("/getDesc")
    public ResultVo getDesc(){
        List<Option> selectOptions = scheduleCourseService.getDesc();
        return ResultUtils.success("查询成功",selectOptions);
    }


    //保存排课
    @PostMapping("/saveSchedule")
    public ResultVo saveSchedule(@RequestBody ScheduleParm scheduleParm){
        scheduleCourseService.saveSchedule(scheduleParm);
        return ResultUtils.success("排课成功!");
    }

    //自动排课
    @PostMapping("/autoSchedule")
    public ResultVo autoSchedule(@RequestBody AutoScheduleParm autoScheduleParm){
        scheduleCourseService.autoSchedule(autoScheduleParm);
        return ResultUtils.success("排课成功!");
    }

    //排课列表查询
    @GetMapping("/getScheduleList")
    public ResultVo getScheduleList(ListParm listParm){
        String teacherId = listParm.getTeacherId();
        String roomId = listParm.getRoomId();
        List<ScheduleCourse> list = scheduleCourseService.findAll(teacherId,roomId);
        if(scheduleCourseService.count(teacherId,roomId) != 1){
            list = scheduleCourseService.conformity(list);
        }
        return ResultUtils.success("查询成功",list);
    }

    //新增排课
    @PostMapping("/addSchedule")
    public ResultVo addSchedule(@RequestBody ScheduleCourse scheduleCourse){
        boolean save = scheduleCourseService.save(scheduleCourse);
        if(save){
            return ResultUtils.success("排课成功!");
        }
        return ResultUtils.error("排课失败!");
    }


    //编辑排课
    @PutMapping("/updateScheduleById")
    public ResultVo updateScheduleById(@RequestBody NewScheduleCourse newScheduleCourse){
        if (newScheduleCourse.getIsDeleted() == 0){
            ScheduleCourse scheduleCourse = newScheduleCourse;
            boolean save = scheduleCourseService.updateById(scheduleCourse);
            if(save){
                return ResultUtils.success("编辑成功!");
            }
        }
        else{
            scheduleCourseService.deleteById(newScheduleCourse.getId());
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }
}
