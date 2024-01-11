package com.itmk.web.teacher.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.teacher.entity.Teacher;
import com.itmk.web.teacher.entity.TeacherParm;
import com.itmk.web.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if(save){
            return ResultUtils.success("新增成功！");
        }
        return ResultUtils.error("新增失败!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody Teacher teacher){
        boolean save = teacherService.updateById(teacher);
        if(save){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{teacherId}")
    public ResultVo delete(@PathVariable("teacherId") Long teacherId){
        boolean b = teacherService.removeById(teacherId);
        if(b){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败!");
    }

    //列表查询
    @GetMapping("/list")
    public ResultVo getList(TeacherParm parm){
        System.out.println("haha");
        System.out.println(parm);
        System.out.println("haha");
        IPage<Teacher> list = teacherService.getList(parm);
        return ResultUtils.success("查询成功",list);
    }
}
