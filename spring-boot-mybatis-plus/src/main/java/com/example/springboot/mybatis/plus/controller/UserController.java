package com.example.springboot.mybatis.plus.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.example.springboot.mybatis.plus.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pud123
 * @since 2018-01-25
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/insert")
    public void insert() {
        User user = new User();
        user.setName("zhangsan");
        user.setAge(20);
        user.setRoleId((long) 1);
        user.insert();
        System.out.println("insert ok");
    }

    @RequestMapping("/delete")
    public void delete() {
        new User().delete(Condition.create().eq("name", "zhangsan"));
        System.out.println("delete ok");
    }

}

