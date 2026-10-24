package com.microservice.product.dao;

import java.util.List;

/**
 * Étape 4 : interface DAO générique.
 * Elle définit les opérations CRUD de base pour n'importe quelle entité T.
 * Le reste de l'application dépend de cette interface et non de l'implémentation Hibernate.
 */
public interface IDao<T> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    T findById(Long id);

    List<T> findAll();
}
