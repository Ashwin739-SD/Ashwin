package com.papl.demo.Configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "professionalAutomotivesEntityManagerFactory",
        transactionManagerRef = "professionalAutomotivesTransactionManager",
        basePackages = { "com.papl.demo.TripData.Repo" }
)
public class professionalAutomotivesConfig {

    @Bean(name = "professionalAutomotivesDataSource")
    @ConfigurationProperties(prefix = "datasource2")
    public DataSource professionalAutomotivesDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "professionalAutomotivesEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean professionalAutomotivesEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                              @Qualifier("professionalAutomotivesDataSource") DataSource professionalAutomotivesDataSource) {
        return builder
                .dataSource(professionalAutomotivesDataSource)
                .packages("com.papl.demo.TripData.Entity")
                .build();
    }

    @Bean(name = "professionalAutomotivesTransactionManager")
    public PlatformTransactionManager professionalAutomotivesTransactionManager(
            @Qualifier("professionalAutomotivesEntityManagerFactory") EntityManagerFactory professionalAutomotivesEntityManagerFactory) {
        return new JpaTransactionManager(professionalAutomotivesEntityManagerFactory);
    }

}

