package com.common.BankData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableTransactionManagement
public class BankDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDataApplication.class, args);
	}

}
