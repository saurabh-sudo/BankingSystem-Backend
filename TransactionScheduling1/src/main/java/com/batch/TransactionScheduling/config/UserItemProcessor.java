package com.batch.TransactionScheduling.config;



import com.common.BankData.entity.Schedule;
import org.springframework.batch.item.ItemProcessor;


public class UserItemProcessor implements ItemProcessor<Schedule, Schedule> {

    @Override
    public Schedule process(Schedule user) throws Exception {
        System.out.println("proxess"+user);
        return user;
    }

}