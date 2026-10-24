package com.microservice.product.config;

// CORRECTION énoncé : l'énoncé importait "org.SessionFactory", qui n'existe pas.
// La bonne classe est org.hibernate.SessionFactory.
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Étape 7 : configuration des transactions.
 *
 * @EnableTransactionManagement : active la prise en compte de @Transactional.
 * Spring entoure alors les beans annotés d'un proxy qui démarre la transaction
 * avant la méthode et fait commit (ou rollback en cas d'exception) après.
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    /**
     * Gestionnaire de transactions pour Hibernate : il ouvre la session Hibernate,
     * la lie au thread (ce qui permet getCurrentSession() dans le DAO) et gère commit / rollback.
     * Le paramètre SessionFactory est injecté par Spring (bean créé dans HibernateConfig).
     */
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
