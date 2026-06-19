package com.example.weatherviewer.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataBaseConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();

        source.setDriverClassName("org.postgresql.Driver");
        source.setUrl("jdbc:postgresql://localhost:5432/app_db");
        source.setUsername("postgres");

        return source;
    }


    @Bean
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource);

        factory.setPackagesToScan("com.example.weatherviewer.entity");

        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        factory.setEntityManagerFactoryInterface(EntityManagerFactory.class);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");


        factory.setJpaProperties(properties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
