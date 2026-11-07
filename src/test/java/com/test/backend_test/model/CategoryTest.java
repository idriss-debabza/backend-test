package com.test.backend_test.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryTest {

    @Test
    void shouldAddProductToCategoryAndMaintainReference() {
        // Arrange
        Category category = new Category(1L, "Informatique", "Matériel tech", null, new ArrayList<>(), new ArrayList<>());
        Product product = new Product(1L, "Clavier", 50.0, 10, new ArrayList<>());

        // Act
        category.getProducts().add(product);

        // Assert
        assertEquals(1, category.getProducts().size());
        assertEquals("Clavier", category.getProducts().get(0).getName());
    }
}