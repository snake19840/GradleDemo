package com.example.demo.Dao;

import com.example.demo.model.MysqlUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepositoryPagingAndSorting extends PagingAndSortingRepository<MysqlUser,Long> {

}
