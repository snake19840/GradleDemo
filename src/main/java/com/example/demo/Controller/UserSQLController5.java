package com.example.demo.Controller;




import com.example.demo.Dao.*;
import com.example.demo.model.Menus;
import com.example.demo.model.MysqlUser;
import com.example.demo.model.Roles;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/sql5")
public class UserSQLController5 {
    private UserService userService;
@Autowired
private RolesRepositoryByName rolesRepositoryByName;
    @Autowired
private UserRepositoryByName userRepositoryByName;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesJPARepository rolesJPARepository;
    /**
     * 页面跳转
     */

    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page){
        return "Thymeleaf/"+page;
    }

    @RequestMapping("/save")
    public String testSave(Roles roles,MysqlUser mysqlUser,Model model){
        String username=mysqlUser.getUsername();
        String password=mysqlUser.getPassword();
        String rolename=roles.getRolename();
       List<MysqlUser> u=this.userRepositoryByName.findByUsernameEquals(username);
       List<Roles> u2=this.rolesRepositoryByName.findByRolename(rolename);

        if (u.size()!=0){
            model.addAttribute("success"," 已经有同名用户");
            return "Thymeleaf/index5";
        }
        if (u2.size()!=0){
            //创建用户
            mysqlUser=new MysqlUser();
            mysqlUser.setUsername(username);
            mysqlUser.setPassword(password);

            roles=u2.get(0);
            roles.getMysqlUsers().add(mysqlUser);
            mysqlUser.setRoles(roles);
            this.userRepository.save(mysqlUser);
            model.addAttribute("success","已有角色名，创建成功");
            return "Thymeleaf/index5";
        }

        //创建用户
         mysqlUser=new MysqlUser();
        mysqlUser.setUsername(username);
        mysqlUser.setPassword(password);

        //创建角色
         roles=new Roles();
        roles.setRolename(rolename);

        //关联
        roles.getMysqlUsers().add(mysqlUser);
        mysqlUser.setRoles(roles);

        //保存
        this.userRepository.save(mysqlUser);

        model.addAttribute("success","创建成功");
        return "Thymeleaf/index5";
    }

    /**
     * 一对多关系查询
     * @param model
     * @return
     */
    @RequestMapping("/searchOneToMany")
    public String testSearchOneToMany(MysqlUser mysqlUser,Model model){
        String username=mysqlUser.getUsername();

        try {
            Long id=this.userRepositoryByName.findByUsernameEquals(username).get(0).getId();
            MysqlUser findOne=this.userRepository.getOne(id);

            Roles roles=findOne.getRoles();
            Map<String,Object> map=new HashMap<>();
           map.put("User",findOne);
            map.put("Role",roles);

            model.addAttribute("map",map);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("success","没有设置角色");
        }
//        model.addAttribute("success",findOne);
        return "Thymeleaf/index5";
    }

    /**
     * 多对多关系创建
     * @param model
     * @return
     */
    @RequestMapping("/createManyToMany")
    @Transactional
    public String testCreateManyToMany(Roles roles,  Model model){
        String rolename=roles.getRolename();
        List<Roles> l=this.rolesRepositoryByName.findByRolename(rolename);
        if (l.size()!=0){
            model.addAttribute("success"," 已经有同名角色");
            return "Thymeleaf/index5";
        }
        //创建角色对象
        Roles r=new Roles();
        r.setRolename(rolename);

        //创建菜单对象
        Menus m=new Menus();
        m.setMenusname("系统管理");
        // m.setFatherid(0) :0表示目录级别
        m.setFatherid(0);

        Menus m2=new Menus();
        m2.setMenusname(rolename+"系统管理");
        //m2.setFatherid(1): 1表示目录级别 目录级别0/1/2/3/4/5/.......
        m2.setFatherid(1);

        //创建关联
        // 角色与菜单的关系,通过角色去取出菜单,加入关联菜单
        r.getMenus().add(m);
        r.getMenus().add(m2);
        //通过菜单去去关系角色
        m.getRoles().add(r);
        m2.getRoles().add(r);

        //保存
        this.rolesJPARepository.save(r);

        model.addAttribute("success"," 创建成功");

        return "Thymeleaf/index5";
    }

    /**
     *  多对多的关系查询
     */
    @RequestMapping("/searchManyToMany")
    @Transactional
    public String searchManyToMany(Roles roles,  Model model){
        String rolename=roles.getRolename();

        try {
            Integer id=this.rolesRepositoryByName.findByRolename(rolename).get(0).getRoleid();
            Roles r=this.rolesJPARepository.getOne(id);
            Set<Menus> menus=r.getMenus();
            Map<String,Object> map=new HashMap<>();
            for (Menus menus1 : menus){
               map.put(menus1.getMenusname(),menus1.getFatherid());
            }
            model.addAttribute("map",map);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("success"," 没有相关内容");
        }

        return "Thymeleaf/index5";
    }

}
