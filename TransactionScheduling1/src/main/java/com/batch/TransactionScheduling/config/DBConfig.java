package com.batch.TransactionScheduling.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DBConfig {

    @Autowired
    Environment environment;


    @Bean
    @Primary
    @Order(1)
    public DataSource datasource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }


    @Bean
    @Primary
    public JpaTransactionManager jpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(datasource());
        return transactionManager;
    }

}
