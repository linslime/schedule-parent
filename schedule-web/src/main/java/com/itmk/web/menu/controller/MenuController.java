package com.itmk.web.menu.controller;

import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.menu.entity.Menu;
import com.itmk.web.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody Menu menu) {
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        boolean save = menuService.save(menu);
        if (save) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody Menu menu) {
        menu.setUpdateTime(new Date());
        boolean save = menuService.updateById(menu);
        if (save) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{menuId}")
    public ResultVo deleteMenu(@PathVariable("menuId") Long menuId) {
        menuService.deleteMenu(menuId);
        return ResultUtils.success("删除成功!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo getList(){
        List<Menu> list = menuService.getList();
        System.out.println("hahah");
        System.out.println(list);
        System.out.println("hahah");
        return ResultUtils.success("查询成功",list);
    }

    //上级菜单树数据
    @GetMapping("/parent")
    public ResultVo getParentList(){
        List<Menu> list = menuService.parentList();
        return ResultUtils.success("查询成功",list);
    }
}
