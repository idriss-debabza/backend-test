package com.test.backend_test.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWhenProductIsValid() {
        Product product = new Product(1L, "PC Gamer", 1500.0, 5, new ArrayList<>());
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertTrue(violations.isEmpty(), "Le produit devrait être valide");
    }

    @Test
    void shouldFailValidationWhenPriceIsNegative() {
        Product product = new Product(1L, "Souris", -10.0, 5, new ArrayList<>());
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertEquals(1, violations.size());
        assertEquals("Le prix doit être strictement positif", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenNameIsBlank() {
        Product product = new Product(1L, "", 50.0, 10, new ArrayList<>());
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertEquals(1, violations.size());
        assertEquals("Le nom du produit est obligatoire", violations.iterator().next().getMessage());
    }
}