package ru.chuikov.Operator.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("ru.chuikov.Operator.repository")
@EnableTransactionManagement
@ComponentScan("ru.chuikov.Operator")
@PropertySource("classpath:db.properties")
public class DataBaseConfig {
    @Resource
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setPackagesToScan(environment.getRequiredProperty("db.entity.package"));

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    public Properties getHibernateProperties() {
        try {

            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");

            properties.load(is);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());

        return manager;
    }

    @Bean
    public DataSource dataSource()
    {
        BasicDataSource ds=new BasicDataSource();
        ds.setUrl(environment.getRequiredProperty("db.url"));
        ds.setDriverClassName(environment.getRequiredProperty("db.driver"));
        ds.setUsername(environment.getRequiredProperty("db.username"));
        ds.setPassword(environment.getRequiredProperty("db.password"));

        ds.setInitialSize(Integer.valueOf(environment.getRequiredProperty("db.initialSize")));
        ds.setMinIdle(Integer.valueOf(environment.getRequiredProperty("db.minIdle")));
        ds.setMaxIdle(Integer.valueOf(environment.getRequiredProperty("db.maxIdle")));
        ds.setTimeBetweenEvictionRunsMillis(Long.valueOf(environment.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        ds.setMinEvictableIdleTimeMillis(Long.valueOf(environment.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        ds.setTestOnBorrow(Boolean.valueOf(environment.getRequiredProperty("db.testOnBorrow")));
        ds.setValidationQuery(environment.getRequiredProperty("db.validationQuery"));
        return ds;
    }
}
