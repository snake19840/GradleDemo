package com.example.demo.Controller;

import com.example.demo.Dao.UserRepository;
import com.example.demo.Dao.UserRepositoryByName;
import com.example.demo.Dao.UserRepositoryCrudRepository;
import com.example.demo.Dao.UserRepositoryQueryAnnotation;
import com.example.demo.model.MysqlUser;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sql2")
public class UserSQLController2 {
    private UserService userService;
    @Autowired
private UserRepositoryCrudRepository userRepositoryCrudRepository;
    /**
     * 页面跳转
     */

    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page){

        return "Thymeleaf/"+page;
    }


    @RequestMapping("/search7")
    @Transactional
    public String search7(MysqlUser user,Model model){

       this.userRepositoryCrudRepository.save(user);
       model.addAttribute("seccess2","保存成功");
        return "Thymeleaf/index2";
    }

    @RequestMapping("/search8")
    @Transactional
    public String updata(MysqlUser user,Model model){
        this.userRepositoryCrudRepository.save(user);
        model.addAttribute("seccess","修改成功");
        return "Thymeleaf/index2";
    }

    @RequestMapping("/search9")
    @Transactional
    public String findOne(MysqlUser user,Model model){
        Long id=user.getId();
        Optional<MysqlUser> user1= this.userRepositoryCrudRepository.findById(user.getId());

        model.addAttribute("success","user1.get()");
        return "Thymeleaf/index2";
    }
    @RequestMapping("/search10")
    @Transactional
    public String findAll(MysqlUser user,Model model){

        Iterable<MysqlUser> user1= this.userRepositoryCrudRepository.findAll();
        model.addAttribute("list",user1);
        return "Thymeleaf/index2";
    }
    @RequestMapping("/search11")

    public String deleteByID(MysqlUser user,Model model){
            String status="";
try {
    this.userRepositoryCrudRepository.deleteById(user.getId());
    status="删除成功";
}catch (Exception e){
    e.printStackTrace();
    status="已经没有删除对象";
}
        model.addAttribute("success",status);
        return "Thymeleaf/index2";
    }


}
