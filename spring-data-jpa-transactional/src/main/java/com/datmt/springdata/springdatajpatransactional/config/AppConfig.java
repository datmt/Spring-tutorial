package com.datmt.springdata.springdatajpatransactional.config;

import com.datmt.springdata.springdatajpatransactional.BankTransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages= {"com.datmt.springdata.springdatajpatransactional"})
@EnableTransactionManagement
public class AppConfig {

    @Bean(name = "default_datasource")
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
        dataSource.setUrl("jdbc:postgresql://localhost:5433/db1");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean(name = "datasource_tx_manager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public BankTransferService bankTransferService() {
        var service = new BankTransferService();
        service.setDataSource(dataSource());
        return service;
    }
}
