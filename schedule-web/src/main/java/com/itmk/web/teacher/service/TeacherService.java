package com.itmk.web.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.teacher.entity.Teacher;
import com.itmk.web.teacher.entity.TeacherParm;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface TeacherService extends IService<Teacher> {
    IPage<Teacher> getList(TeacherParm parm);
}
