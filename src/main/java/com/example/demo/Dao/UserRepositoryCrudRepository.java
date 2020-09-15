package com.example.demo.Dao;

import com.example.demo.model.MysqlUser;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface UserRepositoryCrudRepository extends CrudRepository<MysqlUser,Long>  {
}
