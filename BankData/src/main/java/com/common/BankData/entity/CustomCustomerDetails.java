package com.common.BankData.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomCustomerDetails extends Customer implements UserDetails {

    public CustomCustomerDetails(final Customer customers)
    {
        super(customers);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        System.out.println("getAuthorities");
//
//return getAdminRole()
//        .stream()
//        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
//        .collect(Collectors.toList());

//        Set<GrantedAuthority> authorities = new HashSet<>();
//        adminRole.forEach(ur -> authorities.add(new SimpleGrantedAuthority("ROLE_"+ur.getRoleName())));
//        return authorities;
//  return null;
        return null;
}

@Override
public String getPassword() {
return getPassword();
}

@Override
public String getUsername() {
return getUserName();
}

@Override
public boolean isAccountNonExpired() {
return true;
}

@Override
public boolean isAccountNonLocked() {
return true;
}

@Override
public boolean isCredentialsNonExpired() {
return true;
}

@Override
public boolean isEnabled() {
return true;
}
}
