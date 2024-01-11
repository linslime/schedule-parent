package com.itmk.web.classroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.classroom.entity.ClassRoom;
import com.itmk.web.classroom.entity.ListParm;
import com.itmk.web.classroom.mapper.ClassRoomMapper;
import com.itmk.web.classroom.service.ClassRoomService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Service
public class ClassRoomServiceImpl extends ServiceImpl<ClassRoomMapper, ClassRoom> implements ClassRoomService {
    @Override
    public IPage<ClassRoom> getList(ListParm listParm) {
        //构造分页查询对象
        IPage<ClassRoom> page = new Page<>();
        page.setCurrent(listParm.getCurrentPage());
        page.setSize(listParm.getPageSize());
        //构造查询条件
        QueryWrapper<ClassRoom> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(listParm.getRoomName())){
            query.lambda().like(ClassRoom::getRoomName,listParm.getRoomName());
        }
        return this.baseMapper.selectPage(page,query);
    }
}
