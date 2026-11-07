package com.test.backend_test.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartItemTest {

    @Test
    void shouldInitializeCartItemCorrectly() {
        // Arrange
        Product product = new Product(1L, "Ecran", 200.0, 10, new ArrayList<>());

        // Act
        CartItem item = new CartItem(1L, new Cart(), product, 3);

        // Assert
        assertEquals(3, item.getQuantity());
        assertEquals("Ecran", item.getProduct().getName());

        // Vérification de logique : le sous-total de cet item devrait être 600
        Double expectedSubTotal = item.getProduct().getPrice() * item.getQuantity();
        assertEquals(600.0, expectedSubTotal);
    }
}