package com.itmk.web.passageerFlow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.passageerFlow.entity.ClassRoom;
import com.itmk.web.passageerFlow.entity.ListParm;
import com.itmk.web.passageerFlow.entity.PassagerFlow;
import com.itmk.web.passageerFlow.service.passagerFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/passagerFlow")
public class PassagerFlowController {
    @Autowired
    private passagerFlowService classRoomService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody ClassRoom classRoom){
        classRoomService.save();
        return ResultUtils.success("新增成功!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody PassagerFlow pf){
        classRoomService.update(pf.getId(),pf.getRoomName(),pf.getDateTime(),pf.getBeginTime(),pf.getEndTime(),pf.getPassagerFlow(),pf.getPassagerNumber());
//        boolean save = classRoomService.updateById(pf);
//        if(save){
//
//        }
//        return ResultUtils.error("编辑失败!");
        return ResultUtils.success("编辑成功!");
    }

    //删除
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable("id") Long id){
        classRoomService.removeById(id);
        return ResultUtils.success("删除成功!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo getList(ListParm listParm){

        IPage<PassagerFlow> list = new Page<>();
        list.setRecords(classRoomService.find(listParm));
        list.setTotal(classRoomService.count(listParm));
        return ResultUtils.success("查询成功",list);
    }

}
