package com.microservice.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Étape 6 : configuration Hibernate en Java (remplace l'ancien hibernate.cfg.xml).
 *
 * @Configuration  : classe qui déclare des beans Spring avec @Bean.
 * @PropertySource : charge application.properties ; les valeurs sont ensuite lues via Environment.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class HibernateConfig {

    // Environment donne accès aux propriétés chargées par @PropertySource
    @Autowired
    private Environment env;

    /**
     * Source de données : sait comment ouvrir une connexion JDBC vers MySQL.
     * DriverManagerDataSource ouvre une nouvelle connexion à chaque demande (pas de pool),
     * c'est suffisant pour un TP.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Les 4 valeurs viennent de application.properties (clés jdbc.*)
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    /**
     * Fabrique Spring qui construit la SessionFactory Hibernate.
     * La SessionFactory obtenue est un bean injectable (utilisé dans ProductDaoImpl).
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        // Hibernate passe par la DataSource ci-dessus pour obtenir ses connexions
        sessionFactory.setDataSource(dataSource());
        // packagesToScan : Hibernate cherche les classes @Entity dans ce package,
        // pas besoin de les déclarer une par une.
        sessionFactory.setPackagesToScan("com.microservice.product.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    // Propriétés Hibernate (dialecte, affichage SQL, génération du schéma) lues dans application.properties
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}
