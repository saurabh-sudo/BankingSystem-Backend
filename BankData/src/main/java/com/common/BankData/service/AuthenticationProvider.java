package com.common.BankData.service;



import com.common.BankData.dao.CustomerDao;
import com.common.BankData.entity.CustomCustomerDetails;
import com.common.BankData.entity.Customer;
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
    CustomerDao customerDao;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        //
    }

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        Object token = usernamePasswordAuthenticationToken.getCredentials();
        Customer ud= customerDao.findByToken(token.toString());
        if(ud==null)
        {
             throw new UsernameNotFoundException("Cannot find user with authentication token=" + token);
        }
        return new CustomCustomerDetails(ud);
    }

}
