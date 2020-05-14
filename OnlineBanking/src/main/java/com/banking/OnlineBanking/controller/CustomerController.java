package com.banking.OnlineBanking.controller;


import com.common.BankData.dao.AccountDao;
import com.common.BankData.dao.ScheduleDao;
import com.common.BankData.entity.PrimaryTransaction;
import com.common.BankData.entity.Schedule;
import com.common.BankData.entity.ScheduleList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    @Autowired
   ScheduleDao scheduleDao;

    @PostMapping("/scheduleTransaction")
        public void scheduleJob(@RequestBody ScheduleList scheduleList)
    {
        for (Schedule schedule: scheduleList.getSchedule()) {
                schedule.setStatus("scheduled");

            scheduleDao.save(schedule);
        }
    }






}
