package com.itmk.web.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itmk.jwt.JwtUtils;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.login.entity.LoginParm;
import com.itmk.web.login.entity.LoginResult;
import com.itmk.web.login.entity.MenuResult;
import com.itmk.web.login.entity.RouterVO;
import com.itmk.web.menu.entity.MakeTree;
import com.itmk.web.menu.entity.Menu;
import com.itmk.web.menu.service.MenuService;
import com.itmk.web.user.entity.User;
import com.itmk.web.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginParm loginParm) {
        if (StringUtils.isEmpty(loginParm.getUsername()) || StringUtils.isEmpty(loginParm.getPassword())) {
            return ResultUtils.error("用户名或密码不能为空!");
        }
        //查询用户
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getUsername, loginParm.getUsername()).eq(User::getPassword,
                DigestUtils.md5DigestAsHex(loginParm.getPassword().getBytes()));
        User user = userService.getOne(query);
        if (user == null) {
            return ResultUtils.error("用户名或密码错误!");
        }
        //生成token
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("userId", Long.toString(user.getUserId()));
        String token = jwtUtils.generateToken(map);
        //构造返回值
        LoginResult result = new LoginResult();
        result.setUserId(user.getUserId());
        result.setToken(token);
        result.setUsername(user.getUsername());
        return ResultUtils.success("登录成功", result);
    }

    @GetMapping("/getMenuList")
    public ResultVo getMenuList(Long userId){
        List<Menu> menuList = menuService.getMenuByUserId(userId);
        if(menuList.size() < 1){
            return ResultUtils.error("您暂无菜单权限，请联系管理员!");
        }
        List<Menu> collect = menuList.stream().filter(item -> item != null && !item.getType().equals("2"))
                .collect(Collectors.toList());
        //组装路由数据
        List<RouterVO> router = MakeTree.makeRouter(collect, 0L);
        //权限字段
        Object[] array = menuList.stream().filter(item -> item.getCode() != null).map(item -> item.getCode()).toArray();
        //返回
        MenuResult result = new MenuResult();
        result.setAuthList(array);
        result.setMenuList(router);
        return ResultUtils.success("查询成功",result);
    }
}
