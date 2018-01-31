package com.example.springboot.template.controller;

import com.example.springboot.template.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class PageController {
    @RequestMapping("/frame")
    public String frame() {
        return "frame/frame";
    }

    @RequestMapping("/table")
    public ModelAndView text(ModelMap model) {
        List<User> userList = new ArrayList<User>();
        userList.add(new User() {{
            this.setName("张三");
            this.setAge(20);
        }});
        userList.add(new User() {{
            this.setName("李四");
            this.setAge(25);
        }});
        userList.add(new User() {{
            this.setName("王五");
            this.setAge(30);
        }});
        model.addAttribute("userList", userList);

        HashMap<String, User> userMap = new HashMap<String, User>();
        userMap.put("1", new User() {{
            this.setName("张三");
            this.setAge(20);
        }});
        userMap.put("2", new User() {{
            this.setName("李四");
            this.setAge(25);
        }});
        userMap.put("3", new User() {{
            this.setName("王五");
            this.setAge(30);
        }});
        model.addAttribute("userMap", userMap);
        return new ModelAndView("table", model);
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

        ModelAndView mav = new ModelAndView("variable", model);
        return mav;
    }


}
