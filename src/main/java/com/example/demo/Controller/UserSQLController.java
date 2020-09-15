package com.example.demo.Controller;

import com.example.demo.Dao.UserRepository;
import com.example.demo.Dao.UserRepositoryByName;
import com.example.demo.Dao.UserRepositoryQueryAnnotation;
import com.example.demo.model.MysqlUser;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sql")
public class UserSQLController {
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
@Autowired
private UserRepositoryByName userRepositoryByName;
@Autowired
private UserRepositoryQueryAnnotation userRepositoryQueryAnnotation;
    /**
     * 页面跳转
     */

    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page){

        return "Thymeleaf/"+page;
    }

    @RequestMapping("/save")
    public String saveUser( User user){
System.out.println(user);
        return "Thymeleaf/ok";
    }


    @RequestMapping("/find")
    public String findUser(MysqlUser user, Model model){
        String name=user.getUsername();
        System.out.println(user);
        System.out.println("________________________");
        System.out.println(userRepository.findByUsername(name));
        System.out.println("________________________");
        String passWord="";
        try {if (name!=null){
            passWord=userRepository.findByUsername(name).getPassword();
        }else {
            passWord="请输入用户名";
        }

        }catch (Exception e){
            e.printStackTrace();
            passWord="没有此用户";
        }

        model.addAttribute("password",passWord);

        return "Thymeleaf/index";
    }

    @RequestMapping("/savetest")
    public String saveUser(MysqlUser user,Model model){
String name=user.getUsername();
        String ps=user.getPassword();
        user.setPassword(ps);
        user.setUsername(name);
        this.userRepository.save(user);
        model.addAttribute("status","保存成功");
        return "Thymeleaf/index";
    }

    @RequestMapping("/search1")
    public String search(MysqlUser user, Model model) {
String name=user.getUsername();

        List<MysqlUser> list =this.userRepositoryByName.findByUsernameEquals(name);
        for (MysqlUser mysqlUser :list){
            System.out.println(mysqlUser);
        }
        model.addAttribute("list",list);
        return "Thymeleaf/index";
    }

    @RequestMapping("/search2")
    public String search2(MysqlUser user,Model model){
String name=user.getUsername();
String password=user.getPassword();
System.out.println(name);
        System.out.println(password);
        List<MysqlUser> list =this.userRepositoryByName.findByUsernameAndPassword(name,password);

        for (MysqlUser mysqlUser :list){
            System.out.println(mysqlUser);
        }

        model.addAttribute("list",list);
        return "Thymeleaf/index";
    }

    @RequestMapping("/search3")
    public String search3(MysqlUser user,Model model){
        String name=user.getUsername();
System.out.println(name);
        List<MysqlUser> list =this.userRepositoryByName.findByUsernameLike("%"+name+"%");

        for (MysqlUser mysqlUser :list){
            System.out.println(mysqlUser);
        }

        model.addAttribute("list",list);
        return "Thymeleaf/index";
    }

    @RequestMapping("/search4")
    public String search4(MysqlUser user,Model model){
        String name=user.getUsername();
        System.out.println(name);
        List<MysqlUser> list =this.userRepositoryQueryAnnotation.queryByUsernameUseHQL(name);

        for (MysqlUser mysqlUser :list){
            System.out.println(mysqlUser);
        }

        model.addAttribute("list",list);
        return "Thymeleaf/index";
    }

    @RequestMapping("/search5")
    public String search5(MysqlUser user,Model model){
        String name=user.getUsername();
        System.out.println(name);
        List<MysqlUser> list =this.userRepositoryQueryAnnotation.queryByUsernameUseSQL(name);

        for (MysqlUser mysqlUser :list){
            System.out.println(mysqlUser);
        }
        model.addAttribute("list",list);
        return "Thymeleaf/index";
    }

    @RequestMapping("/search6")
    public String search6(MysqlUser user,Model model){
        String name=user.getUsername();
        Long id=user.getId();
        System.out.println(name);
        System.out.println(id);
//        try {
//            this.userRepositoryQueryAnnotation.updateUsernameByMysqlUserId(name,id);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        this.userRepositoryQueryAnnotation.updateUsernameByMysqlUserId(name,id);

        model.addAttribute("success","修改成功");
        return "Thymeleaf/index";
    }

}
