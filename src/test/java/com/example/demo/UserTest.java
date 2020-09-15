package com.example.demo;


import com.example.demo.Dao.UserRepository;
import com.example.demo.model.MysqlUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.swing.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Test UserMapper")
public class UserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
private static MysqlUser mysqlUser;

    @BeforeAll //在所有测试方法前执行，只执行一次
    static void setUp() {
        mysqlUser = new MysqlUser();
        mysqlUser.setUsername("张三三");
        mysqlUser.setPassword("123456");

    }

    @Test
    @DisplayName("Add user")
    public void saveTest(){
        this.userRepository.save(mysqlUser);
    }
}
