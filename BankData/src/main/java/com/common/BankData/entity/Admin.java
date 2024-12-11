package com.common.BankData.entity;


import com.common.BankData.entity.security.Role;

import javax.persistence.*;
import java.util.List;

@Entity
public class Admin {


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "adminRole", joinColumns = @JoinColumn(name = "adminid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
    public List<Role> adminRole;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;
    @Column(name = "adminName")
    private String adminName;
    private String userName;
    //    @OneToMany(mappedBy = "admin", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
//    @JsonIgnore
//    public List<AdminRole> adminRole;
    private String passwordHash;
    private String bankIFSC;
    private String token;
    public Admin() {

    }

    public Admin(Admin o) {
        this.adminId = o.adminId;
        this.adminName = o.adminName;
        this.adminRole = o.adminRole;
        this.userName = o.userName;
        this.bankIFSC = o.bankIFSC;
        this.passwordHash = o.passwordHash;
        this.token = o.token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public List<Role> getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(List<Role> adminRole) {
        this.adminRole = adminRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getBankIFSC() {
        return bankIFSC;
    }

    public void setBankIFSC(String bankIFSC) {
        this.bankIFSC = bankIFSC;
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        adminRole.forEach(ur -> authorities.add(new Authority(ur.getRoleName())));
//        return authorities;
//    }


}
