package com.common.BankData.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

//@Entity
public class ScheduleList {
   // @Id
    private int id;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Column(name = "schedule")
//    @OneToMany
    List<Schedule> schedule;

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}
