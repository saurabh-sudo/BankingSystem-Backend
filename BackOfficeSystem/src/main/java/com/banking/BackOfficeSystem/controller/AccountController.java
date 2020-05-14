package com.banking.BackOfficeSystem.controller;

import com.common.BankData.dao.AccountDao;
import com.common.BankData.dao.CustomerDao;
import com.common.BankData.entity.Account;
import com.common.BankData.entity.Customer;
import com.common.BankData.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountDao accountDao;

    @Autowired
    CustomerDao customerDao;

//    @Autowired
//    SmsService smsService;

    @GetMapping("/getAll")
    public ResponseEntity getAllAccounts() throws Exception {
        try {
            List<Account> list = new ArrayList<>();
            list = accountDao.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            System.out.println("Error is " + e);

            return new ResponseEntity<>("Username or password is Wrong", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addAccount(@RequestBody Account account) throws Exception {
        try {
            Account acc = accountDao.save(account);
            return ResponseEntity.ok(acc);

        } catch (Exception e) {

            System.out.println("Error is " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        try {
            Optional<Account> acc = accountDao.findById(id);
            return ResponseEntity.ok(acc);

        } catch (Exception e) {

            System.out.println("Error is " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAccount(@RequestBody Account account, @PathVariable long id) throws Exception {
        try {
            account.setId(id);
            if (account.getAccountStatus() == 3 && account.getAccountId() == 0) {

                boolean b = true;
                long number = 0;

                account.setId(id);
                account.setAccountStatus(3);
                while (b) {
                    number = (long) (Math.random() * 100000000 * 1000000);
                    Account existingAcc = accountDao.findByAccountId(number);
                    if (existingAcc != null) {
                        continue;
                    } else {
                        b = false;
                        account.setAccountId(number);


                    }

                }
            }
            Account acc1 = accountDao.saveAndFlush(account);
            Set<Account> accountSet = new HashSet<>();
            accountSet.add(acc1);
            if (acc1 != null && account.getAccountStatus() == 3) {
                long customerId = customerDao.getNextCustomerId();
                SmsService smsService = new SmsService();
                char[] charPassword = smsService.generatePassword(10);
                String password = new String(charPassword);
                //  String password = charPassword.toString();
                Customer customer = new Customer(account.getFirstName() + account.getLastName(),
                        account.getFirstName(), password, accountSet, null, customerId);
                customerDao.save(customer);
                String message = "your password for Bank Account is " + password;
                smsService.sendSms(customerId, String.valueOf(acc1.getPhoneNo()), password);


            }
            return ResponseEntity.ok(acc1);
        } catch (Exception e) {
            System.out.println("Error is " + e);
            return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllByStatus/{status}")
    public ResponseEntity getAllByStatus(@PathVariable int status) {
        List<Account> list = new ArrayList<>();

        try {
            list = accountDao.findByAccountStatus(new Integer(status));
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            System.out.println("Error is " + e);
            return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }
}
