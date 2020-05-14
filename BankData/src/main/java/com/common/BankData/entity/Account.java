package com.common.BankData.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Null;
import javax.ws.rs.DefaultValue;
import java.sql.Date;
import java.util.List;

@Entity
//@JsonIgnoreProperties(ignoreUnknown = true)
public class
Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String bankIfsc;

    private String firstName;
    private String lastName;

    @ColumnDefault("0")
    private long phoneNo;
//@Column(name = "age")
//    private int age=0;

//    @DefaultValue("0.0")
//    @Column(name="balance")
//    private double balance=0.0f;

    @ColumnDefault("0.0")
    @Column(name="balance")
    private double balance=0.0f;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;


    @DefaultValue("0")
    private int accountStatus;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="proofid")
    @JsonManagedReference
    private Proof proof;

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

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneno(long phoneno) {
        this.phoneNo = phoneno;
    }

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

    public Proof getProof() {
        return proof;
    }

    public void setProof(Proof proof) {
        this.proof = proof;
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

    public Account(long id, String bankIfsc, String firstName, String lastName, double balance, Date date, int accountStatus, Proof proof, Customer customer, long accountId, String remarks) {
        this.id = id;
        this.bankIfsc = bankIfsc;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.date = date;
        this.accountStatus = accountStatus;
        this.proof = proof;
        this.customer = customer;
        this.accountId = accountId;
        this.remarks = remarks;
    }

    public Account() {
    }
}
