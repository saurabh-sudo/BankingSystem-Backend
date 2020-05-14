package com.common.BankData.service;


import com.common.BankData.dao.CustomerDao;
import com.common.BankData.entity.CustomCustomerDetails;
import com.common.BankData.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

	@Autowired
	private CustomerDao customerDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = customerDao.findByUserNameContainingIgnoreCase(username);
		if (user == null) {
			LOG.warn("Username {} not found", username);
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return new CustomCustomerDetails(user);
	}
}
