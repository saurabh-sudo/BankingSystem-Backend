package com.common.BankData.service;

import com.common.BankData.dao.AdminDao;
import com.common.BankData.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminService {

    @Autowired
    AdminDao adminDao;

    public Admin findByToken(String token) {
        Admin admin= adminDao.findByToken(token);
        return admin;
    }
}
