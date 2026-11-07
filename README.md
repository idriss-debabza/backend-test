# Test Technique - Développeur Full Stack / Backend Java (Artere RH)

API Backend développée avec **Spring Boot** dans le cadre d'un test technique e-commerce (durée : 2h).

##  Fonctionnalités implémentées

### User Story 1 : Catalogue de Produits & Catégories
* **Modèle hiérarchique :** Gestion des catégories et sous-catégories imbriquées sans limite de profondeur.
* **Propriétés :**
    * *Catégorie :* Nom, description, catégorie parente, sous-catégories et produits associés.
    * *Produit :* Nom, prix (strictement positif), quantité en stock.
* **Opérations CRUD complètes :** Ajout, modification et suppression pour les produits et les catégories.
* **Liaison flexible :** Association Many-to-Many entre les catégories et les produits (un produit peut appartenir à plusieurs catégories et inversement).

### User Story 2 : Panier d'Achat
* **Gestion du panier :** Création d'un panier d'achat, ajout, modification de quantité et suppression d'articles.
* **Calcul automatique :** Calcul dynamique et en temps réel du montant total du panier (`totalAmount`).

---

## Stack Technique
* **Java 21 (LTS)**
* **Spring Boot 3.3.5**
* **Spring Data JPA / Hibernate**
* **Base de données H2**
* **Lombok** (pour alléger le code boilerplate)
* **Validation API (Jakarta Validation)**
* **JUnit 5 & MockMvc** (Tests unitaires et d'intégration)

---

## Initialisation des données
L'application intègre un `CommandLineRunner` (`DataInitializer`) qui peuple automatiquement la base de données au démarrage avec :

---

## Comment exécuter le projet ?

### Prérequis
* Avoir installé **Java 21** sur votre machine.
* **IntelliJ IDEA** ou tout autre IDE Java.

### Lancement depuis IntelliJ
Ouvrez le projet dans IntelliJ IDEA.

### Lancement via le terminal (Maven Wrapper)
Ouvrez un terminal à la racine du projet et exécutez :
```bash
./mvnw spring-boot:run