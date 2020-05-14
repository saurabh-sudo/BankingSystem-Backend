package com.banking.BackOfficeSystem.service;

import com.common.BankData.dao.AdminDao;
import com.common.BankData.entity.Admin;
import com.common.BankData.entity.CustomAdminDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    AdminDao adminDao;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        Object token = usernamePasswordAuthenticationToken.getCredentials();
        Admin ud = adminDao.findByToken(token.toString());
        if (ud == null) {
            throw new UsernameNotFoundException("Cannot find user with authentication token=" + token);
        }
        return new CustomAdminDetails(ud);
    }

}
