package com.microservice.product.dao;

import java.util.List;

/**
 * Étape 4 : interface DAO générique.
 * Elle définit les opérations CRUD de base pour n'importe quelle entité T.
 * Le reste de l'application dépend de cette interface et non de l'implémentation Hibernate.
 */
public interface IDao<T> {

    // Ajoute une nouvelle entité en base
    void save(T entity);

    // Met à jour une entité qui existe déjà
    void update(T entity);

    // Supprime l'entité de la base
    void delete(T entity);

    // Cherche une entité par sa clé primaire (renvoie null si elle n'existe pas)
    T findById(Long id);

    // Renvoie toutes les lignes de la table
    List<T> findAll();
}
