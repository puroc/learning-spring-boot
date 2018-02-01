package com.example.springboot.template.controller;

import com.example.springboot.template.domain.User;
import com.example.springboot.template.table.TableData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class PageController {

    @RequestMapping("/user/list")
    @ResponseBody
    public String list() {
        User u1 = new User();
        u1.setId((long) 1);
        u1.setName("zhangsan");
        u1.setPassword("123");
        u1.setPhone("188");
        User u2 = new User();
        u2.setId((long) 2);
        u2.setName("lisi");
        u2.setPassword("456");
        u2.setPhone("138");
        TableData<User> td = new TableData<User>();
        td.getData().add(u1);
        td.getData().add(u2);
        return td.toJson();
    }

    @RequestMapping("/frame")
    public String frame() {
        return "frame/frame";
    }

    @RequestMapping("/variable")
    public ModelAndView variable(ModelMap model) {
        User user = new User();
        user.setName("李四");
        model.addAttribute("user", user);

        List<User> userList = new ArrayList<User>();
        userList.add(new User() {{
            this.setName("1-list");
        }});
        userList.add(new User() {{
            this.setName("2-list");
        }});
        userList.add(new User() {{
            this.setName("3-list");
        }});
        model.addAttribute("userList", userList);

        HashMap<String, User> userMap = new HashMap<String, User>();
        userMap.put("1", new User() {{
            this.setName("1-map");
        }});
        userMap.put("2", new User() {{
            this.setName("2-map");
        }});
        userMap.put("3", new User() {{
            this.setName("3-map");
        }});
        model.addAttribute("userMap", userMap);

        ModelAndView mav = new ModelAndView("test/variable", model);
        return mav;
    }


}
