package com.common.BankData.entity;


import java.util.List;

//@Entity
public class ScheduleList {
    //    @Column(name = "schedule")
//    @OneToMany
    List<Schedule> schedule;
    // @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}
