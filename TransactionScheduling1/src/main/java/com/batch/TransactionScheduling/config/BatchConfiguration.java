package com.batch.TransactionScheduling.config;



import com.common.BankData.entity.Schedule;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.ws.rs.Produces;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration  extends DefaultBatchConfigurer {



//    @Autowired
//    Environment environment;
//
//
//    @Bean
//    @Primary
//    @Order(1)
//    public DataSource datasource() {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
//        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
//        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
//        return dataSource;
//    }

    @Override
    protected JobRepository createJobRepository() throws Exception {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

@Autowired
DataSource datasource;



    @Autowired
    JpaTransactionManager trxm;


    @Bean
    public JdbcCursorItemReader<Schedule> reader() throws PropertyVetoException {
        Date today = new Date();
        JdbcCursorItemReader<Schedule> reader = new JdbcCursorItemReader<Schedule>();
        reader.setDataSource(datasource);
        reader.setSql("SELECT scheduleid,accountid,amount,dates,recipientaccountno,recipientName,status,type FROM schedule");
        reader.setRowMapper(new UserRowMapper());

        return reader;
    }


    public class UserRowMapper implements RowMapper<Schedule>{

        @Override
        public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
            Schedule schedule = new Schedule();
            schedule.setScheduleid(rs.getInt("scheduleid"));
            schedule.setAccountId(rs.getLong("accountid"));
            schedule.setAmount(rs.getFloat("amount"));
            schedule.setDates(rs.getDate("dates"));
            schedule.setRecipientAccountNo(rs.getLong("recipientaccountno"));
            schedule.setRecipientName((rs.getString("recipientName")));
            schedule.setStatus(rs.getString("status"));
            schedule.setType(rs.getString("type"));

            return schedule;
        }

    }

    @Bean
    public UserItemProcessor processor(){
        return new UserItemProcessor();
    }

    @Autowired
    DBWriter dbWriter;

    @Bean
    public FlatFileItemWriter<Schedule> writer(){

        FlatFileItemWriter<Schedule> writer = new FlatFileItemWriter<Schedule>();
        writer.setResource(new ClassPathResource("users.csv"));
        writer.setLineAggregator(new DelimitedLineAggregator<Schedule>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<Schedule>() {{
                setNames(new String[] { "id", "name" });
            }});
        }});

        return writer;
    }


    @Bean
    public Step step1() throws PropertyVetoException {
        return stepBuilderFactory.get("step1").transactionManager(trxm).<Schedule, Schedule> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(dbWriter)
                .build();
    }

    @Bean
    @Transactional
    public Job exportUserJob() throws PropertyVetoException {
        return jobBuilderFactory.get("exportUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

}