package com.example.springboot.ui.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by puroc on 17/6/12.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @RequestMapping("/list")
    public ModelAndView list(ModelMap model){
        model.addAttribute("name","zhangsan");
        return new ModelAndView("person/list",model);
    }
}
