package com.common.BankData.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.sql.Date;


@Entity
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Proof
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int proofId;
    private long uuid;
    private String passportNumber;
    private String emailId;
    private Date dob;


    @OneToOne(mappedBy = "proof")
    @JsonBackReference
    private Account acc;

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    private int age;

    public int getProofId() {
        return proofId;
    }

    public void setProofId(int proofId) {
        this.proofId = proofId;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
