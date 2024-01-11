package com.itmk.web.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.role.entity.Role;
import com.itmk.web.role.service.RoleService;
import com.itmk.web.schedule.entity.SelectOption;
import com.itmk.web.user.entity.PageParm;
import com.itmk.web.user.entity.User;
import com.itmk.web.user.service.UserService;
import com.itmk.web.user_role.entity.UserRole;
import com.itmk.web.user_role.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody User user) {
        //根据用户名查询
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getUsername,user.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        User one = userService.getOne(query);
        if(one != null){
            return ResultUtils.error("用户名被占用!");
        }
        userService.addUser(user);
        return ResultUtils.success("新增成功!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody User user) {
        //根据用户名查询
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getUsername,user.getUsername());
        User one = userService.getOne(query);
        if(one != null && one.getUserId() != user.getUserId()){
            return ResultUtils.error("用户名被占用!");
        }
        userService.editUser(user);
        return ResultUtils.success("编辑成功!");
    }

    //删除
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId) {
        userService.deletUser(userId);
        return ResultUtils.success("删除成功!");
    }

    //列表查询
    @GetMapping("/list")
    public ResultVo getList(PageParm parm) {
        IPage<User> list = userService.getUserList(parm);
        return ResultUtils.success("查询成功", list);
    }

    //角色列表查询
    @GetMapping("/role")
    public ResultVo getRole() {
        List<Role> list = roleService.list();
        List<SelectOption> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item -> {
                    SelectOption option = new SelectOption();
                    option.setValue(item.getRoleId());
                    option.setLabel(item.getRoleName());
                    selectOptions.add(option);
                });
        return ResultUtils.success("查询成功", selectOptions);
    }
    //根据id查询用户信息
    @GetMapping("/getUser")
    public ResultVo getUserById(Long userId){
        User user = userService.getById(userId);
        //查角色id
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query.lambda().eq(UserRole::getUserId,userId);
        UserRole one = userRoleService.getOne(query);
        user.setRoleId(one.getRoleId());
        return ResultUtils.success("查询成功",user);
    }
}
