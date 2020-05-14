package com.common.BankData.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.security.AccessControlContext;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long customerId;
    private String customerName;
//    private long accountId;
    private  String userName;
    private String password;

    @ColumnDefault("0")
    private long userId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customeridref")
    private Set<Account> accounts;

    private String token;

    public Customer(final Customer o) {
        this.customerId=o.customerId;
        this.customerName=o.customerName;
        this.userName=o.userName;
        this.password=o.password;
        this.token=o.token;
      //  this.accountId=o.accountId;
        this.accounts=o.accounts;



    }


    public Customer() {
    }

    public Customer(long customerId, String customerName, String userName, String password, Set<Account> accounts, String token,long userId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.userName = userName;
        this.password = password;
        this.accounts = accounts;
        this.token = token;
        this.userId = userId;
    }
    //this constructor is used to create a new Customer, when the account of that particular user is approved
    public Customer(String customerName, String userName, String password, Set<Account> accounts, String token,long userId) {
        this.customerName = customerName;
        this.userName = userName;
        this.password = password;
        this.accounts = accounts;
        this.token = token;
        this.userId=userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUser_id(long userId) {
        this.userId = userId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

//    public long getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(long accountId) {
//        this.accountId = accountId;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
