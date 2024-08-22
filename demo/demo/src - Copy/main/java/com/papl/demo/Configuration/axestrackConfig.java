package com.papl.demo.Configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "axestrackEntityManagerFactory",
        transactionManagerRef = "axestrackTransactionManager",
        basePackages = { "com.papl.demo.AxestracDb.Repo" }
)
public class axestrackConfig {

    @Bean(name="axestrackDataSource")
    @Primary
    @ConfigurationProperties(prefix="datasource")
    public DataSource axestrackDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "axestrackEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean axestrackEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                @Qualifier("axestrackDataSource") DataSource axestrackDataSource) {
        return builder
                .dataSource(axestrackDataSource)
                .packages("com.papl.demo.AxestracDb.Entity")
                .build();
    }

    @Bean(name = "axestrackTransactionManager")
    public PlatformTransactionManager axestrackTransactionManager(
            @Qualifier("axestrackEntityManagerFactory") EntityManagerFactory axestrackEntityManagerFactory) {
        return new JpaTransactionManager(axestrackEntityManagerFactory);
    }

}