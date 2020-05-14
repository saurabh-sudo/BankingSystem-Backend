package com.banking.OnlineBanking.controller;


import com.common.BankData.dao.AccountDao;
import com.common.BankData.entity.Account;
import com.common.BankData.entity.OtherAccount;
import com.common.BankData.entity.PrimaryTransaction;
import com.common.BankData.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    AccountDao accountDao;

    @Autowired
    TransferService transferService;

    @PostMapping("/betweenAccounts")
    public ResponseEntity betweenAccounts(@RequestBody PrimaryTransaction transaction) throws Exception {
        long recipient = transaction.getRecipientAccountNo();
        Account recipientAccount = accountDao.findByAccountId(transaction.getRecipientAccountNo());
        Account primaryAccount = accountDao.findByAccountId(transaction.getAccountId());
        java.util.Date d = new Date();
        if (recipientAccount != null) {
            transferService.addMoneyToRecipient(recipientAccount, primaryAccount, transaction.getAmount(), transaction);

        } else {
            OtherAccount RecipientAccount = new OtherAccount(transaction.getAmount(), (java.sql.Date) d, recipient);
            transferService.addMoneyToRecipientOfAnotherBank(RecipientAccount, primaryAccount, transaction.getAmount(), transaction);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/transactionHistory/{accountId}")
    public ResponseEntity getTransactionList(@PathVariable long accountId) {

        Set<PrimaryTransaction> pt = transferService.getTransactionHistoryByAccountID(accountId);
        List<PrimaryTransaction> pp = new ArrayList<>();
        for (PrimaryTransaction p : pt
        ) {
            pp.add(p);
        }

        return ResponseEntity.ok(pp);

    }


    @GetMapping("/balance/{accountId}")
    public ResponseEntity getBalance(@PathVariable long accountId) {

        Account account = accountDao.findByAccountId(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/balanceAmountOnly/{accountId}")
    public ResponseEntity balanceAmountOnly(@PathVariable long accountId) {

        Account account = accountDao.findByAccountId(accountId);
        return ResponseEntity.ok(account.getBalance());
    }
}
