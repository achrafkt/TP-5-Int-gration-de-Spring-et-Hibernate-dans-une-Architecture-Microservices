package com.microservice.product.dao;

import com.microservice.product.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Étape 5 : implémentation Hibernate du DAO pour l'entité Product.
 *
 * @Repository : déclare la classe comme bean Spring de la couche d'accès aux données
 * (détectée par @ComponentScan) et active la traduction des exceptions Hibernate
 * en exceptions Spring (DataAccessException).
 */
@Repository
public class ProductDaoImpl implements IDao<Product> {

    // SessionFactory créée par HibernateConfig (étape 6) et injectée par Spring
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * getCurrentSession() renvoie la session liée à la transaction en cours.
     * C'est Spring (via @Transactional + HibernateTransactionManager) qui ouvre la session,
     * fait le commit / rollback et la ferme : on n'a donc pas à le faire ici.
     * Attention : appelé hors transaction, cela lève une HibernateException.
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Product product) {
        getSession().save(product);   // INSERT
    }

    @Override
    public void update(Product product) {
        getSession().update(product); // UPDATE
    }

    @Override
    public void delete(Product product) {
        getSession().delete(product); // DELETE
    }

    @Override
    public Product findById(Long id) {
        return getSession().get(Product.class, id); // SELECT par clé primaire (null si absent)
    }

    @Override
    public List<Product> findAll() {
        // Requête HQL : on interroge l'entité "Product", pas directement la table
        return getSession().createQuery("from Product", Product.class).getResultList();
    }
}
