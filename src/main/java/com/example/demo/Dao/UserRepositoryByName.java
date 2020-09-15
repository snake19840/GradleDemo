package com.example.demo.Dao;

import com.example.demo.model.MysqlUser;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository接口的方法名称查询
 */
public interface UserRepositoryByName extends Repository<MysqlUser,Long> {
//方法的名称必须要遵守驼峰式命名规则findBy(关键字)+属性名称(首字母要大写)+查询条件(首字母大写)
    List<MysqlUser> findByUsernameEquals(String name);
    List<MysqlUser> findByUsernameAndPassword(String name,String password);
    List<MysqlUser> findByUsernameLike(String name);

}
