package com.banking.OnlineBanking;

import com.common.BankData.BankDataApplication;
import com.common.BankData.dao.XConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.common.BankData")
@EnableScheduling
@Import({XConfiguration.class, BankDataApplication.class})
public class OnlineBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingApplication.class, args);
	}

}
