package com.itmk.web.classroom.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.classroom.entity.ClassRoom;
import com.itmk.web.classroom.entity.ListParm;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface ClassRoomService extends IService<ClassRoom> {
    IPage<ClassRoom> getList(ListParm listParm);
}
