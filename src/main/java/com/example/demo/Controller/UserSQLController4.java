package com.example.demo.Controller;




import com.example.demo.Dao.UserRepositoryPagingAndSorting;
import com.example.demo.Dao.UserRepositorySpecification;
import com.example.demo.model.MysqlUser;
import com.example.demo.model.Paging;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Controller
@RequestMapping("/sql4")
public class UserSQLController4 {
    private UserService userService;
    @Autowired
private UserRepositorySpecification userRepositorySpecification;
    /**
     * 页面跳转
     */

    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page){

        return "Thymeleaf/"+page;
    }


    @RequestMapping("/search1")
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
/**
 * Specification<MysqlUser>
 *     封装查询条件
 */
        Specification<MysqlUser> spce=new Specification<MysqlUser>() {
            //Predicate:封装单个查询条件

            /**
             *
             * @param root 查询对象属性的封装
             * @param query 封装了查询各部分的信息  select,from,order by
             * @param criteriaBuilder 查询条件的构造器,定义不同的查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<MysqlUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // where name = "俞健"
                /**
                 *  参数一: 查询的属性
                 *  参数二: 条件的值
                 */
                Predicate pre=criteriaBuilder.equal();
                return null;
            }
        }

        //遍历当前页
        Iterable<MysqlUser> list=this.userRepositorySpecification.findAll(spce);


        model.addAttribute("list",list);
        //总条数
        model.addAttribute("listItems",lists.getTotalElements());
        //总页数
        model.addAttribute("listPages",lists.getTotalPages());

        return "Thymeleaf/index4";
    }

}
