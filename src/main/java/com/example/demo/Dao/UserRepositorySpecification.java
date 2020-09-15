package com.example.demo.Dao;

import com.example.demo.model.MysqlUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaRepository<T, ID>
 *
 *     T表示映射的实体类
 *     ID表示要映射实体类主键的类型是什么
 */

public interface UserRepositorySpecification extends JpaRepository<MysqlUser, Long>, JpaSpecificationExecutor<MysqlUser> {
}
