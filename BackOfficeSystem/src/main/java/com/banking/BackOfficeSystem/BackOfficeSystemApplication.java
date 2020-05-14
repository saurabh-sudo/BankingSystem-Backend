package com.banking.BackOfficeSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.common.BankData")
public class BackOfficeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackOfficeSystemApplication.class, args);
        System.out.println("BackOffice Application Started");
    }
}
