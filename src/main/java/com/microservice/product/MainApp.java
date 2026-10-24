package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Étape 11 : application principale.
 * Elle utilise la couche service pour enregistrer un produit en base,
 * puis affiche le nombre total de produits.
 */
public class MainApp {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            // CORRECTION énoncé : on demande le bean par son interface ProductService.
            // À cause de @Transactional, Spring remplace ProductServiceImpl par un proxy JDK
            // qui implémente seulement l'interface. getBean(ProductServiceImpl.class) lèverait
            // une NoSuchBeanDefinitionException, car aucun bean n'est de ce type.
            ProductService productService = context.getBean(ProductService.class);

            productService.addProduct(new Product("Microservice Gateway", 149.99));

            System.out.println("Nombre total de produits en base : "
                    + productService.getAllProducts().size());
        }
    }
}
