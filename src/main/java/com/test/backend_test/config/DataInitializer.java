package com.test.backend_test.config;

import com.test.backend_test.model.Category;
import com.test.backend_test.model.Product;
import com.test.backend_test.repository.CategoryRepository;
import com.test.backend_test.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            // 1. Création des Produits
            Product p1 = new Product(null, "iPhone 15 Pro", 1199.0, 10, new ArrayList<>());
            Product p2 = new Product(null, "MacBook Pro M3", 2399.0, 5, new ArrayList<>());
            Product p3 = new Product(null, "Casque Sony WH-1000XM5", 349.0, 20, new ArrayList<>());
            Product p4 = new Product(null, "Chaise Ergonomique Bureau", 299.0, 8, new ArrayList<>());

            productRepository.saveAll(List.of(p1, p2, p3, p4));

            // 2. Création de la Catégorie Racine (Parent) avec listes initialisées
            Category rootCategory = new Category(null, "E-Commerce Principal", "Catalogue général", null, new ArrayList<>(), new ArrayList<>());
            categoryRepository.save(rootCategory);

            // 3. Création des Sous-Catégories rattachées à la Racine
            Category subCatTech = new Category(null, "High-Tech", "Appareils électroniques", rootCategory, new ArrayList<>(), new ArrayList<>());
            Category subCatFurniture = new Category(null, "Mobilier", "Meubles pour maison et bureau", rootCategory, new ArrayList<>(), new ArrayList<>());
            categoryRepository.saveAll(List.of(subCatTech, subCatFurniture));

            // 4. Liaisons Produits <-> Catégories (ManyToMany)
            subCatTech.getProducts().add(p1);
            subCatTech.getProducts().add(p2);
            subCatTech.getProducts().add(p3);
            categoryRepository.save(subCatTech);

            subCatFurniture.getProducts().add(p4);
            categoryRepository.save(subCatFurniture);

            System.out.println("--> Données de test initialisées avec succès !");
        };
    }
}