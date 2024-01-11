package com.itmk.web.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.teacher.entity.Teacher;
import com.itmk.web.teacher.entity.TeacherParm;
import com.itmk.web.teacher.mapper.TeacherMapper;
import com.itmk.web.teacher.service.TeacherService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public IPage<Teacher> getList(TeacherParm parm) {
        //构造分页对象
        IPage<Teacher> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurrentPage());
        //构造查询条件
        QueryWrapper<Teacher> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getTeacherName())){
            query.lambda().like(Teacher::getTeacherName,parm.getTeacherName());
        }
        if(StringUtils.isNotEmpty(parm.getRoomId())){
            query.lambda().like(Teacher::getTeacherStores,parm.getRoomId());
        }
        return this.baseMapper.selectPage(page,query);
    }
}
