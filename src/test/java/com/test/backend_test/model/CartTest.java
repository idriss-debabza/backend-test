package com.test.backend_test.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {

    @Test
    void shouldCalculateTotalAmountCorrectly() {
        // Arrange
        Product iphone = new Product(1L, "iPhone", 1000.0, 10, null);
        Product coque = new Product(2L, "Coque", 20.0, 50, null);

        Cart cart = new Cart();
        // Ajout de 2 iPhones et 1 Coque
        cart.getItems().add(new CartItem(1L, cart, iphone, 2));
        cart.getItems().add(new CartItem(2L, cart, coque, 1));

        // Act : Exécution de la méthode à tester
        Double total = cart.getTotalAmount();

        // Assert : Vérification du résultat (2 * 1000 + 1 * 20 = 2020) par exemple
        assertEquals(2020.0, total, "Le total du panier doit être la somme exacte des (prix * quantité)");
    }
}