package com.example.springboot.shiro.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.springboot.shiro.entity.User;
import com.example.springboot.shiro.service.IUserService;
import com.example.springboot.shiro.table.TableData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        List<User> list = userService.selectList(new EntityWrapper<User>());
        TableData<User> td = new TableData<User>();
        td.getData().addAll(list);
        return td.toJson();
    }

    @RequiresPermissions("admin:user:create")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addUser(User user){
        System.out.println(user.getUsername());
    }


}

