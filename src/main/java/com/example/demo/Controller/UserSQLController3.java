package com.example.demo.Controller;




import com.example.demo.Dao.UserRepositoryPagingAndSorting;
import com.example.demo.model.MysqlUser;
import com.example.demo.model.Paging;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sql3")
public class UserSQLController3 {
    private UserService userService;
    @Autowired
private UserRepositoryPagingAndSorting userRepositoryPagingAndSorting;
    /**
     * 页面跳转
     */

    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page){

        return "Thymeleaf/"+page;
    }


    @RequestMapping("/search1")
    public String testSort(MysqlUser user, Model model){
        System.out.println("排序");
        Sort.Order order=new Sort.Order(Sort.Direction.DESC,"id");
        Sort.Order order1=new Sort.Order(Sort.Direction.DESC,"password");
        Iterable<MysqlUser> list = this.userRepositoryPagingAndSorting.findAll(Sort.by(order1,order));
       model.addAttribute("list",list);
        return "Thymeleaf/index3";
    }

    @RequestMapping("/search2")
    public String testPaging(MysqlUser user, Paging paging, Model model){
        //Pageable ;封装了分页的参数,当前页,每页显示的条数,注意:他的当前页是从0开始的
        //PageRequest(page,size) page:当前页,size:每页显示的条数
        Sort.Order order=new Sort.Order(Sort.Direction.DESC,"id");
        PageRequest pageable = PageRequest.of(paging.getPage()-1, paging.getSize());
        Page<MysqlUser> lists = this.userRepositoryPagingAndSorting.findAll(pageable);
        //遍历当前页
       Iterable<MysqlUser> list=lists.getContent();

        model.addAttribute("list",list);
        //总条数
        model.addAttribute("listItems",lists.getTotalElements());
        //总页数
        model.addAttribute("listPages",lists.getTotalPages());

        return "Thymeleaf/index3";
    }



    @RequestMapping("/search3")
    public String testPagingAndSort(MysqlUser user, Paging paging, Model model){
        //Pageable ;封装了分页的参数,当前页,每页显示的条数,注意:他的当前页是从0开始的
        //PageRequest(page,size) page:当前页,size:每页显示的条数
        Sort.Order order=null;
        if (paging.isDir()){
            order =new Sort.Order(Sort.Direction.ASC,paging.getSortObj());
        }else {
            order=new Sort.Order(Sort.Direction.DESC,paging.getSortObj());
        }

        PageRequest pageable = PageRequest.of(paging.getPage()-1, paging.getSize(),Sort.by(order));
        Page<MysqlUser> lists =this.userRepositoryPagingAndSorting.findAll(pageable);

        //遍历当前页
        Iterable<MysqlUser> list=lists.getContent();

        model.addAttribute("list",list);
        //总条数
        model.addAttribute("listItems",lists.getTotalElements());
        //总页数
        model.addAttribute("listPages",lists.getTotalPages());

        return "Thymeleaf/index3";
    }

}
