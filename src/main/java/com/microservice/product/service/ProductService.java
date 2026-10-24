package com.microservice.product.service;

import com.microservice.product.entity.Product;

import java.util.List;

/**
 * Étape 8 : interface de la couche service (logique métier).
 * Les classes clientes (MainApp) dépendent de ce contrat et non de l'implémentation.
 */
public interface ProductService {

    void addProduct(Product product);

    List<Product> getAllProducts();
}
