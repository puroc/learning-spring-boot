package com.example.springboot.shiro.controller;


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


    @RequestMapping("/test")
    @ResponseBody
    public String haha() {
        return "123";
    }


}

