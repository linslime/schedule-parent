package com.itmk.web.classroom.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.classroom.entity.ClassRoom;
import com.itmk.web.classroom.entity.ListParm;
import com.itmk.web.classroom.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/classroom")
public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody ClassRoom classRoom){
        boolean save = classRoomService.save(classRoom);
        if(save){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody ClassRoom classRoom){
        boolean save = classRoomService.updateById(classRoom);
        if(save){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{roomId}")
    public ResultVo delete(@PathVariable("roomId") Long roomId){
        boolean b = classRoomService.removeById(roomId);
        if(b){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo getList(ListParm listParm){
        IPage<ClassRoom> list = classRoomService.getList(listParm);
        return ResultUtils.success("查询成功",list);
    }

}
