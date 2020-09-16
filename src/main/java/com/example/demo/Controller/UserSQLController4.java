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
import java.util.ArrayList;
import java.util.List;

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

/**
 * Specification<MysqlUser>
 *     封装查询条件
 */
        Specification<MysqlUser> spec=new Specification<MysqlUser>() {
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
                 *  参数一: 查询的条件属性
                 *  参数二: 条件的值
                 */
                Predicate pre=criteriaBuilder.equal(root.get(paging.getSortObj()),user.getUsername());
                return pre;
            }
        };

        //遍历当前页
        try {
            Iterable<MysqlUser> list=this.userRepositorySpecification.findAll(spec);
            model.addAttribute("list",list);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("success","没有对应值");
        }
        return "Thymeleaf/index4";
                }



    @RequestMapping("/search3")
    public String testPagingAndSorts(MysqlUser user, Paging paging, Model model){

/**
 * Specification<MysqlUser>
 *     封装多条件查询条件
 */
        Specification<MysqlUser> spec=new Specification<MysqlUser>() {
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
                // where name = "俞健" and password="1"
            /*    List<Predicate> listPre=new ArrayList<>();
                listPre.add(criteriaBuilder.equal(root.get("username"),user.getUsername()));
                listPre.add(criteriaBuilder.equal(root.get("password"),user.getPassword()));
                Predicate[] arr=new  Predicate[listPre.size()];*/

               /* return criteriaBuilder.and(listPre.toArray(arr));*/
                //where (name = "俞健" or password="1") and id="1"
//                return criteriaBuilder.and((criteriaBuilder.or(criteriaBuilder.equal(root.get("username"),user.getUsername()),criteriaBuilder.equal(root.get("password"),user.getPassword()))), criteriaBuilder.equal(root.get("id"),user.getId()));
                return criteriaBuilder.and(criteriaBuilder.equal(root.get("username"),user.getUsername()),criteriaBuilder.equal(root.get("password"),user.getPassword()));
            }
        };
//        Sort.Order order=new Sort.Order(Sort.Direction.DESC,"id");
        //遍历当前页
        try {

            Iterable<MysqlUser> list=this.userRepositorySpecification.findAll(spec);
           if (!list.iterator().hasNext()){
               model.addAttribute("success","没有对应值");
           }else {
               model.addAttribute("list",list);
           }

        }catch (Exception e){
            e.printStackTrace();

            model.addAttribute("success","没有对应值");
        }

        return "Thymeleaf/index4";
    }

    @RequestMapping("/search4")
    public String testPagingAndSortsOrder(MysqlUser user, Paging paging, Model model){

/**
 * Specification<MysqlUser>
 *     封装多条件查询条件
 */
        Specification<MysqlUser> spec=new Specification<MysqlUser>() {
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

                return criteriaBuilder.and(criteriaBuilder.equal(root.get("username"),user.getUsername()),criteriaBuilder.equal(root.get("password"),user.getPassword()));
            }
        };
        Sort.Order order=null;
        if (paging.isDir()){
            order=new Sort.Order(Sort.Direction.DESC,"id");
        }else {
             order=new Sort.Order(Sort.Direction.ASC,"id");
        }

        //遍历当前页
        try {

            Iterable<MysqlUser> list=this.userRepositorySpecification.findAll(spec,Sort.by(order));
            if (!list.iterator().hasNext()){
                model.addAttribute("success","没有对应值");
            }else {
                model.addAttribute("list",list);
            }

        }catch (Exception e){
            e.printStackTrace();

            model.addAttribute("success","没有对应值");
        }

        return "Thymeleaf/index4";
    }

        }
