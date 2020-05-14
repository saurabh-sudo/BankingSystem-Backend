package com.batch.TransactionScheduling;

import com.common.BankData.BankDataApplication;
import com.common.BankData.dao.XConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import({XConfiguration.class, BankDataApplication.class})
@EnableScheduling
public class TransactionSchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionSchedulingApplication.class, args);
	}

}
