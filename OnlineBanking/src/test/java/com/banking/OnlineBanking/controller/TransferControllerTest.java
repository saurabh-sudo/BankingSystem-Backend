package com.banking.OnlineBanking.controller;

import com.banking.OnlineBanking.TestUtils;
import com.banking.OnlineBanking.config.SecurityConfig;
import com.common.BankData.dao.*;
import com.common.BankData.entity.Account;
import com.common.BankData.entity.Customer;
import com.common.BankData.entity.PrimaryTransaction;
import com.common.BankData.service.AdminService;
import com.common.BankData.service.AuthenticationProvider;
import com.common.BankData.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.annotation.MultipartConfig;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(TransferController.class)
@EnableAutoConfiguration
@MultipartConfig
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AuthenticationProvider authenticationProvider;

//    @MockBean
//    SecurityConfig securityConfig;

    @MockBean
    CustomerDao customerDao;

    @MockBean
    AccountDao accountDao;

    @MockBean
    TransferService transferService;

    @MockBean
    TransferDao transferDao;

    @MockBean
    OtherBankAccountDao otherBankAccountDao;

    @MockBean
    ScheduleDao scheduleDao;

    @MockBean
    AdminDao adminDao;

    @MockBean
    AdminService adminService;


    private final String URL = "/transfer/balance/";

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void betweenAccounts() throws Exception {

        PrimaryTransaction e1 = new PrimaryTransaction(null, "hello", "completed", 12323, "Sameer", 28339960751126l, 28339960751126l, null, "Normal Payment");


        final String URLTransactionList = "/transfer/betweenAccounts";
// prepare data and mock's behaviour
        Set<PrimaryTransaction> empList = buildTransaction();
        //  transferService.getTransactionHistoryByAccountID(accountId);
        //  when(transferDao.findByAccountId(28339960751126L)).thenReturn((Set<PrimaryTransaction>) empList);

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URLTransactionList).header("Authorization", "")
                .content(asJsonString(
                        e1
                ))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        // verify that service method was called once
//        verify(transferService).getTransactionHistoryByAccountID(any(Long.class));

        // get the List<Employee> from the Json response


//        Account resultEmployee = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Account.class);
//
//
//        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), result.getResponse().getStatus());
        //  assertNotNull("Employees not found", empListResult);
        //   assertEquals("Incorrect Employee List", empList.size(), 2);

    }

    @Test
    public void getTransactionList() throws Exception {
        final String URLTransactionList = "/transfer/transactionHistory/28339960751126";
// prepare data and mock's behaviour
        Set<PrimaryTransaction> empList = buildTransaction();
        //  transferService.getTransactionHistoryByAccountID(accountId);
        when(transferDao.findByAccountId(28339960751126L)).thenReturn((Set<PrimaryTransaction>) empList);

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URLTransactionList).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        // verify that service method was called once
        verify(transferService).getTransactionHistoryByAccountID(any(Long.class));

        // get the List<Employee> from the Json response
        TypeToken<List<PrimaryTransaction>> token = new TypeToken<List<PrimaryTransaction>>() {
        };
        @SuppressWarnings("unchecked")
        List<PrimaryTransaction> empListResult = TestUtils.jsonToList(result.getResponse().getContentAsString(), token);

        for (PrimaryTransaction empList1 : empListResult
        ) {
            System.out.println(empList1);

        }


        //  assertNotNull("Employees not found", empListResult);
        assertEquals("Incorrect Employee List", empList.size(), 2);
    }

    private Set<PrimaryTransaction> buildTransaction() {
        PrimaryTransaction e1 = new PrimaryTransaction(null, "hello", "completed", 12323, "Sameer", 28339960751126l, 28339960751126l, null, "Normal Payment");
        PrimaryTransaction e2 = new PrimaryTransaction(null, "hello", "completed", 12323, "Sameer", 28339960751126l, 28339960751126l, null, "Normal Payment");
        Set<PrimaryTransaction> trans = new HashSet<>();
        //   Employee e2 = new Employee(2l, "bytes2", "tree2", "Senior developer", 16000);
        trans.add(e1);
        trans.add(e2);
        return trans;
    }


    @Test
    public void getBalance() throws Exception {

        String str = "2020-04-06";
        Date date = Date.valueOf(str);

        Account account = new Account(182, null, "Saurabh", "Mishra", 1553.23, date, 3, null, null, 28339960751126L, "Applied approved");

        // prepare data and mock's behaviour
        //   Employee empStub = new Employee(1l, "bytes", "tree", "developer", 12000);
        when(accountDao.findByAccountId(any(Long.class))).thenReturn(account);

        // execute
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(28339960751126l)).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        // verify that service method was called once
        verify(accountDao).findByAccountId(any(Long.class));

        Account resultEmployee = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Account.class);
        assertNotNull(resultEmployee);
        assertEquals(28339960751126l, resultEmployee.getAccountId());
    }


}