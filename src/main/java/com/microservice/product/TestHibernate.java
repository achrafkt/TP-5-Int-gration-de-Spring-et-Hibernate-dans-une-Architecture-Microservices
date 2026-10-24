package com.microservice.product;

import com.microservice.product.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Étape 10 : test de démarrage.
 * Si le contexte se crée sans exception, c'est que la DataSource, la SessionFactory
 * (connexion MySQL + création de la table) et les transactions sont bien configurées.
 */
public class TestHibernate {

    public static void main(String[] args) {
        // try-with-resources : le contexte est fermé à la fin (fermeture de la SessionFactory)
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            System.out.println("Contexte Spring initialisé avec succès !");
        }
    }
}
