package com.microservice.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Étape 3 : entité JPA Product.
 * Chaque objet Product correspond à une ligne de la table "products".
 * Hibernate s'appuie sur les annotations javax.persistence pour faire le mapping objet <-> table.
 */
@Entity                     // indique à Hibernate que cette classe est persistante
@Table(name = "products")   // nom de la table en base (sinon ce serait "Product")
public class Product {

    @Id                                                   // clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // valeur générée par MySQL (AUTO_INCREMENT)
    private Long id;

    @Column(nullable = false)   // colonne NOT NULL
    private String name;

    @Column(nullable = false)
    private double price;

    // Constructeur vide obligatoire : Hibernate l'utilise pour recréer les objets lus en base
    public Product() {
    }

    // Constructeur pratique pour créer un produit dans le code (l'id est généré par la base)
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
