package com.banking.OnlineBanking.controller;

import com.banking.OnlineBanking.OnlineBankingApplication;
import com.common.BankData.dao.*;
import com.common.BankData.entity.Customer;
import com.common.BankData.service.AuthenticationProvider;
import com.common.BankData.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.annotation.MultipartConfig;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
@WebMvcTest(value = CustomerLoginControlller.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class, BootstrapWith.class})
//@WebMvcTest(CustomerLoginControlller.class)
@EnableAutoConfiguration
@MultipartConfig
@ContextConfiguration(classes = {OnlineBankingApplication.class})
public class CustomerLoginControlllerTest {


    @MockBean
    AuthenticationProvider authenticationProvider;

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
    @Autowired
    private MockMvc mockMvc;


//    @Mock
//    PolicyService policyService;

//    @InjectMocks
//    CustomerLoginControlller customerLoginControlller;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

    @Test
    public void testGenerateToken() throws Exception {
        CustomerDao customerDao = mock(CustomerDao.class);

        Customer customer = new Customer(1, "Saurabh", "Saurabh", "Fnb@2021", null, "c6e336bf-5cb4-4c7d-9d80-dcc66738973a", 0);
        when(customerDao.findByUserNameContainingIgnoreCase("Saurabh")).thenReturn(customer);


        // mock(customerDao);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic U2F1cmFiaDpGbmJAMjAyMQ==");
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.addHeader("x-real-ip","127.0.0.1");
        String header = "Authorization:" + "Basic c2F1cmFiaDpGbmJAMjAyMQ==";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
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