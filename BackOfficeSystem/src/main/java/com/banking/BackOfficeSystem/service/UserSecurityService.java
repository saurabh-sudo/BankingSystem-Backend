package com.banking.BackOfficeSystem.service;

import com.common.BankData.dao.AdminDao;
import com.common.BankData.entity.Admin;
import com.common.BankData.entity.CustomAdminDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    /**
     * The application logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private AdminDao adminDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = adminDao.findByUserName(username);
        if (user == null) {
            LOG.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new CustomAdminDetails(user);
    }
}
