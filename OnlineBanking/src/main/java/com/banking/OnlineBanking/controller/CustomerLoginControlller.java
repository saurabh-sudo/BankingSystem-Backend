package com.banking.OnlineBanking.controller;

import com.common.BankData.dao.CustomerDao;
import com.common.BankData.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/login/customer/api")
@RestController
public class CustomerLoginControlller {
    @Autowired
    CustomerDao customerDao;


//    @PostMapping("/secured/token")
//    public ResponseEntity generateToken(@RequestHeader("Authorization") String authString, HttpServletRequest rs, HttpServletResponse res) throws Exception {
//        System.out.println(authString);


    @PostMapping("/secured/token")
    public ResponseEntity generateToken(@RequestHeader("Authorization") String authString, HttpServletRequest rs, HttpServletResponse res) throws Exception {
        System.out.println(authString);
        String[] authParts = authString.split("\\s+");

//        Map<String, String> map = new HashMap<String, String>();
//
//        Enumeration headerNames = rs.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String key = (String) headerNames.nextElement();
//            String value = rs.getHeader(key);
//            map.put(key, value);
//        }
//        String header=map.get("Authorization");
//
//        System.out.println(header);
//
   String decodedAuth = "";
//        String[] authParts = header.split("\\s+");
        String authInfo = authParts[1];
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
            return new ResponseEntity<>("BAD REQUEST",new HttpHeaders(), HttpStatus.BAD_REQUEST);

        }
        decodedAuth = new String(bytes);
        String username = decodedAuth.split(":")[0];
        long userId = Long.parseLong(username);

        String enteredPassword = decodedAuth.split(":")[1];
        username=username.toLowerCase();

        Customer customer = customerDao.findByUserId(userId);
        if(customer==null)
        {
            System.out.println("customer is null");
            return new ResponseEntity<>("Username not found",new HttpHeaders(),HttpStatus.UNAUTHORIZED);
        }
        else
        {
            if(customer.getPassword().equals(enteredPassword)){
                String token = UUID.randomUUID().toString();
                customer.setToken(token);
                customerDao.save(customer);
                return ResponseEntity.ok(customer);
            }
            else
            {
                return new ResponseEntity<>("Username or password is Wrong",new HttpHeaders(),HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
