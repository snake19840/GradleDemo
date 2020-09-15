package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/{page}")
    public String showInfo(@PathVariable String page, Integer id , String name, Model model){
    model.addAttribute("id",id);
    model.addAttribute("name",name);
        return "Thymeleaf/"+page;
    }
}
