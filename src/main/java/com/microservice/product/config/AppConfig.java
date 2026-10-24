package com.microservice.product.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Étape 9 : configuration principale, point d'entrée du contexte Spring.
 *
 * @ComponentScan parcourt com.microservice.product et ses sous-packages et enregistre
 * automatiquement les classes annotées @Repository, @Service et @Configuration
 * (donc aussi HibernateConfig et TransactionConfig).
 */
@Configuration
@ComponentScan("com.microservice.product")
public class AppConfig {
}
