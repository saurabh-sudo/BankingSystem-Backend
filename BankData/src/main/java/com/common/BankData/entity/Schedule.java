package com.common.BankData.entity;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.*;
import java.util.Date;


@Entity(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleid;
    private Date dates;
    private long recipientAccountNo;
    private String status;
    private  String recipientName;
    private double amount;

    private String schedule_type;

    public String getSchedule_type() {
        return schedule_type;
    }

    public void setSchedule_type(String schedule_type) {
        this.schedule_type = schedule_type;
    }

    private String type;

    public Schedule() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(nullable = true)
    private long accountId;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Schedule(int scheduleid, Date dates, long recipientAccountNo, String status, String recipientName, double amount, String type, long accountId) {
        this.scheduleid = scheduleid;
        this.dates = dates;
        this.recipientAccountNo = recipientAccountNo;
        this.status = status;
        this.recipientName = recipientName;
        this.amount = amount;
        this.type = type;
        this.accountId = accountId;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

//    @ManyToOne
//    ScheduleList scheduleList;
//
//    public ScheduleList getScheduleList() {
//        return scheduleList;
//    }
//
//    public void setScheduleList(ScheduleList scheduleList) {
//        this.scheduleList = scheduleList;
//    }


    public int getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }

    public long getRecipientAccountNo() {
        return recipientAccountNo;
    }

    public void setRecipientAccountNo(long recipientAccountNo) {
        this.recipientAccountNo = recipientAccountNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
