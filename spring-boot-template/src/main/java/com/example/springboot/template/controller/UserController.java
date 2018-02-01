package com.example.springboot.template.controller;

import com.example.springboot.template.domain.User;
import com.example.springboot.template.table.TableData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/user/list")
    @ResponseBody
    public String list() {
        TableData<User> td = new TableData<User>();
        for (int i = 0; i < 100; i++) {
            User u1 = new User();
            u1.setId((long) i);
            u1.setName("zhangsan");
            u1.setPassword("123");
            u1.setPhone("188");
            td.getData().add(u1);
        }
        return td.toJson();
    }
}
