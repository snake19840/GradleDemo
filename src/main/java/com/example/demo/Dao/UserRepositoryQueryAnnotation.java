package com.example.demo.Dao;

import com.example.demo.model.MysqlUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepositoryQueryAnnotation extends Repository<MysqlUser,Long> {
    @Query("from MysqlUser where username=?1 ")
    List<MysqlUser> queryByUsernameUseHQL(String name);

    @Query(value = "select * from mysql_user a where a.username=?",nativeQuery = true)
    List<MysqlUser> queryByUsernameUseSQL(String username);

    @Query("update MysqlUser  set username = ?1 where id = ?2")
    @Modifying
    void updateUsernameByMysqlUserId(String name,Long id);
}
