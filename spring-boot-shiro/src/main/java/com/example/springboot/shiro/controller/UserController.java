package com.example.springboot.shiro.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.example.springboot.shiro.entity.User;
import com.example.springboot.shiro.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pud123
 * @since 2018-01-27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequiresPermissions("admin:user:view")
    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        User user = userService.selectOne(Condition.create().eq("username", "admin"));
        return user.getUsername();
    }


}

