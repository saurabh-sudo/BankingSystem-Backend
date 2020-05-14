package com.banking.OnlineBanking.controller;

import com.banking.OnlineBanking.OnlineBankingApplication;
import com.banking.OnlineBanking.TestUtils;
import com.common.BankData.dao.*;
import com.common.BankData.entity.Account;
import com.common.BankData.entity.Admin;
import com.common.BankData.entity.Customer;
import com.common.BankData.entity.PrimaryTransaction;
import com.common.BankData.service.AuthenticationProvider;
import com.common.BankData.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.annotation.MultipartConfig;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@EnableAutoConfiguration
////@MultipartConfig
////@ContextConfiguration(classes = {OnlineBankingApplication.class})
////@WebMvcTest(value = CustomerLoginControlller.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class, BootstrapWith.class})
////@WebMvcTest(controllers = CustomerLoginControlller.class)
//@ExtendWith(SpringExtension.class)
//@DataJpaTest(excludeAutoConfiguration = BootstrapWith.class)
////@SpringBootTest(classes = OnlineBankingApplication.class)
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=validate"
//})
@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerLoginControlller.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class, BootstrapWith.class})
//@WebMvcTest(CustomerLoginControlller.class)
@EnableAutoConfiguration
@MultipartConfig
@ContextConfiguration(classes = {OnlineBankingApplication.class})
public class CustomerLoginControlllerTest {


   @Autowired
    private MockMvc mockMvc;

//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }


//    @Autowired
//    private MockMvc mockMvc;

    @MockBean
    AuthenticationProvider authenticationProvider;




    @MockBean
    Customer customer;

    @MockBean
    AccountDao accountDao;

    @MockBean
    TransferService transferService;

    @MockBean
    CustomerDao customerDao;

    @MockBean
    TransferDao transferDao;

    @MockBean
    OtherBankAccountDao otherBankAccountDao;

    @MockBean
    ScheduleDao scheduleDao;


    @MockBean
    AdminDao adminDao;



//    @Mock
//    PolicyService policyService;

//    @InjectMocks
//    CustomerLoginControlller customerLoginControlller;



    @Before
    public void setup() {

        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(customerLoginControlller).build();

    }



    @After
    public void tearDown() throws Exception {
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }







    @Test
    public void testGenerateToken() throws Exception {
        CustomerDao customerDao = mock(CustomerDao.class);

        Customer customer= new Customer(1,"Saurabh", "Saurabh", "Fnb@2021" ,null, "c6e336bf-5cb4-4c7d-9d80-dcc66738973a",0) ;
            when(customerDao.findByUserNameContainingIgnoreCase("Saurabh")).thenReturn(customer);


           // mock(customerDao);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic U2F1cmFiaDpGbmJAMjAyMQ==");
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.addHeader("x-real-ip","127.0.0.1");
        String header="Authorization:" +"Basic c2F1cmFiaDpGbmJAMjAyMQ==";
        MvcResult result= mockMvc.perform( MockMvcRequestBuilders
                .post("/login/customer/api/secured/token").header("Authorization", "Basic c2F1cmFiaDpGbmJAMjAyMQ==")
              //  .header("Authorization", "Basic c2F1cmFiaDpGbmJAMjAyMQ==")
              ).andReturn();


        int status = result.getResponse().getStatus();
        // assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        // verify that service method was called once
       // verify(customerDao).findByUserNameContainingIgnoreCase(any(String.class));

  //    Customer customer1 = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Customer.class);
        assertEquals("Fnb@2021", customer.getPassword());

    }
}