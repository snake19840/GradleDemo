package com.example.demo.Controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Springboot整合jsp
 */

@Controller
public class UserController {
    /**
     * 处理请示,产生数据
     */

    @RequestMapping("/showUser")
    public String showUser(Model model){
        Map<String,Object> map=new HashMap<>();
        List<User> list=new ArrayList<>();
        list.add(new User(1,"张三","20"));
        list.add(new User(2,"李四","22"));
        list.add(new User(3,"王五","25"));

        map.put("张三","一");
        map.put("张四","二");
        map.put("张五","三");
        map.put("张六","四");

        //需要一个Model对象
        model.addAttribute("list",list);
        model.addAttribute("datekey",new Date());
        model.addAttribute("sex","男");
        model.addAttribute("ID","2");
        model.addAttribute("map",map);
        //跳转视图
        return "Thymeleaf/userList";
//        return "userList";
//        return "jsp/userList";
    }

    @RequestMapping("/show2")
    public String show2Info(HttpServletRequest request,Model model){
        request.setAttribute("req","HttpServletRequest");
        request.getSession().setAttribute("sess","HttpSession");
        request.getSession().getServletContext().setAttribute("app","Application");
        return "Thymeleaf/show2";
    }
}
