package com.microservice.product.service;

import com.microservice.product.dao.IDao;
import com.microservice.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Étape 8 : implémentation de la couche service.
 *
 * @Service       : bean Spring de la couche métier (détecté par @ComponentScan).
 * @Transactional : chaque méthode publique s'exécute dans une transaction gérée par Spring
 *                  (commit si tout va bien, rollback si une RuntimeException est levée).
 *
 * CORRECTION énoncé : la classe doit implémenter ProductService
 * (l'énoncé demandait l'interface mais ne l'utilisait pas).
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    // On dépend de l'interface IDao : Spring injecte ProductDaoImpl, le seul bean IDao<Product>
    @Autowired
    private IDao<Product> productDao;

    @Override
    public void addProduct(Product product) {
        // Règle métier : un prix négatif est refusé (la transaction est annulée)
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        productDao.save(product);
    }

    // readOnly = true : simple lecture, Hibernate peut optimiser (pas de flush des modifications)
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
}
