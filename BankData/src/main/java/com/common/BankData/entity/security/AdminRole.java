package com.common.BankData.entity.security;



import javax.persistence.*;

//
////@Entity
////@Table
//public class AdminRole {
//   // @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long adminRoleId;
//
//    public AdminRole(Admin user, Role role) {
//        this.admin = user;
//        this.role = role;
//    }
//
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "admin_id")
//    private Admin admin;
//
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "role_id")
//    private Role role;
//
//    public long getAdminRoleId() {
//        return adminRoleId;
//    }
//
//    public void setAdminRoleId(long adminRoleId) {
//        this.adminRoleId = adminRoleId;
//    }
//
//    public Admin getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(Admin admin) {
//        this.admin = admin;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//}
