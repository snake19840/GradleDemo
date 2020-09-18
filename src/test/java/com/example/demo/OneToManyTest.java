package com.example.demo;



import com.example.demo.Dao.UserRepository;
import com.example.demo.model.MysqlUser;
import com.example.demo.model.Roles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Table;

/**
 * 一对多关系测试
 */


@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest(classes = DemoApplication.class)
public class OneToManyTest {
    @Autowired
    private UserRepository userRepository;

    /**
     * 一对多关系的添加
     */
    @Test
    public void testSave(){
        //创建用户
        MysqlUser mysqlUser=new MysqlUser();
        mysqlUser.setUsername("俞健");
        mysqlUser.setPassword("yu123");

        //创建角色
        Roles roles=new Roles();
        roles.setRolename("管理员");

        //关联
        roles.getMysqlUsers().add(mysqlUser);
        mysqlUser.setRoles(roles);

        //保存
        this.userRepository.save(mysqlUser);
    }
}
