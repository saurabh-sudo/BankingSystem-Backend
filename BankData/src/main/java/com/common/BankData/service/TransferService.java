package com.common.BankData.service;


import com.common.BankData.dao.AccountDao;
import com.common.BankData.dao.OtherBankAccountDao;
import com.common.BankData.dao.ScheduleDao;
import com.common.BankData.dao.TransferDao;
import com.common.BankData.entity.Account;
import com.common.BankData.entity.OtherAccount;
import com.common.BankData.entity.PrimaryTransaction;
import com.common.BankData.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import javax.persistence.PersistenceUnit;
import javax.transaction.TransactionScoped;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransferService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    OtherBankAccountDao otherBankAccountDao;
    @Autowired
    TransferDao transferDao;

    @Autowired
    ScheduleDao scheduleDao;





    @Transactional
    public Boolean addMoneyToRecipient(Account recipientAccount, Account primaryAccount, double amount, PrimaryTransaction transaction) throws Exception {


        try {
            double moneyPresent = primaryAccount.getBalance() - amount;
            if (moneyPresent > 0) {


                recipientAccount.setBalance(recipientAccount.getBalance() + amount);
                primaryAccount.setBalance(primaryAccount.getBalance() - amount);

                accountDao.save(recipientAccount);
                accountDao.save(primaryAccount);

                Date d = new Date();
                Instant instant = Instant.now();

                LocalDateTime ld=LocalDateTime.now(Clock.systemUTC());

                PrimaryTransaction pt = new PrimaryTransaction(d, transaction.getDescription(), "completed", transaction.getAmount(),
                        transaction.getRecipientName(), transaction.getRecipientAccountNo(), transaction.getAccountId(),ld,transaction.getType());

                transferDao.save(pt);
                return true;

            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }

 @Transactional
    public Boolean addMoneyToRecipientOfAnotherBank(OtherAccount recipientAccount, Account primaryAccount, double amount, PrimaryTransaction transaction) throws Exception {


        try {
            double moneyPresent = primaryAccount.getBalance() - amount;
            if (moneyPresent > 0) {


                recipientAccount.setBalance(recipientAccount.getBalance() + amount);
                primaryAccount.setBalance(primaryAccount.getBalance() - amount);

                otherBankAccountDao.save(recipientAccount);
                accountDao.save(primaryAccount);

                Date d = new Date();
                Instant instant = Instant.now();

                LocalDateTime ld=LocalDateTime.now(Clock.systemUTC());

                PrimaryTransaction pt = new PrimaryTransaction(d, transaction.getDescription(), "completed", transaction.getAmount(),
                        transaction.getRecipientName(), transaction.getRecipientAccountNo(), transaction.getAccountId(),ld,transaction.getType());

                transferDao.save(pt);
                return true;

            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }

    public Set<PrimaryTransaction> getTransactionHistoryByAccountID(long accountId) {
        List<PrimaryTransaction> transactionList=new ArrayList<>();
        Set<PrimaryTransaction> trans=new HashSet<>();
        Set<PrimaryTransaction> trans1=new HashSet<>();
        trans=transferDao.findByAccountId(accountId);
        trans1=transferDao.findByRecipientAccountNo(accountId);
        trans.addAll(trans1);

//        transactionList=transferDao.findByAccountId(accountId);

//        List<PrimaryTransaction> transactionList1=new ArrayList<>();
//        transactionList=transferDao.findByRecipientAccountNo(accountId);
//      //  transactionList.addAll(transactionList1);
//
//        Optional.ofNullable(transactionList1).ifPresent(transactionList::addAll);

//        Collections.sort(trans, new Comparator<PrimaryTransaction>() {
//            public int compare(PrimaryTransaction o1, PrimaryTransaction o2) {
//                if (o1.getDate() == null || o2.getDate() == null)
//                    return 0;
//                return o1.getDate().compareTo(o2.getDate());
//            }
//        });

        return trans;



    }


    public void deleteASchedule(Schedule sd) {

       int a=scheduleDao.removeByScheduleid(sd.getScheduleid());
    }
}
