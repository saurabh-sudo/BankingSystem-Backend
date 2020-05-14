package com.batch.TransactionScheduling.config;



import com.common.BankData.dao.AccountDao;

import com.common.BankData.dao.ScheduleDao;
import com.common.BankData.entity.Account;
import com.common.BankData.entity.PrimaryTransaction;
import com.common.BankData.entity.Schedule;
import com.common.BankData.service.TransferService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class DBWriter implements ItemWriter<Schedule> {

//    @Autowired
//    private ScheduleDao userRepository;

    @Autowired
    ScheduleDao scheduleDao;

    @Autowired
    AccountDao accountDao;

    @Autowired
    TransferService transferService;



    @Override
    @Transactional
    public void write(List<? extends Schedule> list) throws Exception {
        List<Schedule> ad= (List<Schedule>) list;
        Date today = new Date();
        for (Schedule sd:ad
             ) {
            if(DateUtils.isSameDay(today,sd.getDates()))
            {

                long recipient = sd.getRecipientAccountNo();
                Account recipientAccount = accountDao.findByAccountId(recipient);
                Account primaryAccount = accountDao.findByAccountId(sd.getAccountId());
                PrimaryTransaction pt=new PrimaryTransaction(today,"Scheduled Transaction","null",sd.getAmount(),sd.getRecipientName() ,sd.getRecipientAccountNo(),sd.getAccountId(),null,sd.getType());
                pt.setDate(today);
                if (recipientAccount != null) {
                    Boolean b=transferService.addMoneyToRecipient(recipientAccount, primaryAccount, sd.getAmount(), pt);
                    if (b)
                    {
                        System.out.println("delete by id"+sd.getScheduleid());
                     //   transferService.deleteASchedule(sd);

               sd.setStatus("completed");
               transferService.deleteASchedule(sd);
                    }
                    else
                    {
                        sd.setStatus("failed");
                         scheduleDao.save(sd);
                    }

                }

            }
            else {
                continue;

            }

          //  userRepository.save(sd);
        }


    }
}
