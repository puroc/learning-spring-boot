package com.example.springboot.template;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @RequestMapping("/text")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("name", "pud");
        return new ModelAndView("index", model);
    }


}
