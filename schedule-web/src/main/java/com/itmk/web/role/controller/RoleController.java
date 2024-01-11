package com.itmk.web.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.role.entity.AssignParm;
import com.itmk.web.role.entity.AssignVo;
import com.itmk.web.role.entity.ListParm;
import com.itmk.web.role.entity.Role;
import com.itmk.web.role.service.RoleService;
import com.itmk.web.role_menu.entiy.SaveAssign;
import com.itmk.web.role_menu.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody Role role){
        boolean save = roleService.save(role);
        if(save){
            return ResultUtils.success("新增成功");
        }
        return ResultUtils.error("新增失败!");
    }

    //新增
    @PutMapping
    public ResultVo edit(@RequestBody Role role){
        boolean save = roleService.updateById(role);
        if(save){
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{roleId}")
    public ResultVo delete(@PathVariable("roleId") Long roleId){
        boolean save = roleService.removeById(roleId);
        if(save){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败!");
    }

    //查询列表
    @GetMapping("/list")
    public ResultVo getList(ListParm listParm){
        IPage<Role> list = roleService.getList(listParm);
        return ResultUtils.success("查询成功",list);
    }

    //查询分配权限回显
    @GetMapping("/getAssingShow")
    public ResultVo getAssingShow(AssignParm parm){
        AssignVo vo = roleService.getAssignShow(parm);
        return ResultUtils.success("查询成功",vo);
    }

    //分配权限保存
    @PostMapping("/assignSave")
    public ResultVo assignSave(@RequestBody SaveAssign saveAssign){
        roleMenuService.assignSve(saveAssign);
        return ResultUtils.success("分配权限成功!");
    }
}
