package com.itmk.web.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.course.entity.Course;
import com.itmk.web.course.entity.CourseParm;
import com.itmk.web.course.service.CourseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //新增课程
    @PostMapping
    public ResultVo add(@RequestBody Course course) {

        boolean save = courseService.save(course);
        if(save){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    //编辑课程
    @PutMapping
    public ResultVo edit(@RequestBody Course course) {
        boolean save = courseService.updateById(course);
        if(save){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{courseId}")
    public ResultVo delete(@PathVariable("courseId") Long courseId){
        boolean save = courseService.removeById(courseId);
        if(save){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }


    @GetMapping("/list")
    public ResultVo getList(CourseParm courseParm){
        System.out.println("asdf");
        System.out.println(courseParm);
//        courseParm.setRoomName("");
//        System.out.println(courseParm.getRoomName());
//        System.out.println(courseService.getList(courseParm));
        System.out.println("asdf");
        //构造分页对象
        IPage<Course> list = new Page<>();
        list.setRecords(courseService.getList(courseParm));
        list.setTotal(courseService.count(courseParm));

        return ResultUtils.success("查询成功",list);
    }
}
