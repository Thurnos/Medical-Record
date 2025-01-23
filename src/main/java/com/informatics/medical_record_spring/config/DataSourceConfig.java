package com.informatics.medical_record_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.informatics.medical_record_spring.config.CustomDriver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/medical_record");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

}