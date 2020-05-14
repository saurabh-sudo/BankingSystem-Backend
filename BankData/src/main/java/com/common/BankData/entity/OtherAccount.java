package com.common.BankData.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;
import java.sql.Date;

@Entity
public class OtherAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String bankIfsc;

    private String firstName;
    private String lastName;
//@Column(name = "age")
//    private int age=0;

    //@DefaultValue("0.0")
    @Column(name="balance")
    private double balance=0.0f;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;


    @DefaultValue("0")
    private int accountStatus;


//    @OneToMany(mappedBy = "primaryAccount", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<PrimaryTransaction> primaryTransactionList;

    @ManyToOne
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Nullable
    private long accountId;

    private String remarks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }


    public String getBankIfsc() {
        return bankIfsc;
    }

    public void setBankIfsc(String bankIfsc) {
        this.bankIfsc = bankIfsc;
    }

    public OtherAccount(double balance, Date date, long accountId) {
        this.balance = balance;
        this.date = date;
        this.accountId = accountId;
    }

//    public List<PrimaryTransaction> getPrimaryTransactionList() {
//        return primaryTransactionList;
//    }
//
//    public void setPrimaryTransactionList(List<PrimaryTransaction> primaryTransactionList) {
//        this.primaryTransactionList = primaryTransactionList;
//    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


//    public void setAge(int age) {
//        this.age = age;
//    }

}
