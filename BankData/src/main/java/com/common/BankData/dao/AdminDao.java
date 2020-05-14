package com.common.BankData.dao;


import com.common.BankData.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends CrudRepository<Admin,Long> {
    Admin findByUserName(String username);
    Admin findByToken(String token);
}
