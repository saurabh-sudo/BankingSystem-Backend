package com.common.BankData.entity.security;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;

    private String RoleName;


    public Role() {

    }


//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<AdminRole> userRoles = new HashSet<>();

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}
