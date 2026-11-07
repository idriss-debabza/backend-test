package com.test.backend_test.controller;

import com.test.backend_test.model.Cart;
import com.test.backend_test.model.CartItem;
import com.test.backend_test.model.Product;
import com.test.backend_test.repository.CartItemRepository;
import com.test.backend_test.repository.CartRepository;
import com.test.backend_test.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartController(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    // 1. Créer un nouveau panier vide
    @PostMapping
    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    // 2. Voir le panier et son montant total
    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Panier non trouvé"));
    }

    // 3. Ajouter un produit au panier
    @PostMapping("/{cartId}/products/{productId}")
    public Cart addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Panier non trouvé"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé"));

        // Vérifie si le produit est déjà dans le panier pour simplement mettre à jour la quantité
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(null, cart, product, quantity);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    // 4. Modifier la quantité d'un produit déjà dans le panier
    @PutMapping("/{cartId}/items/{itemId}")
    public Cart updateItemQuantity(@PathVariable Long cartId, @PathVariable Long itemId, @RequestParam Integer quantity) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item non trouvé"));

        item.setQuantity(quantity);
        cartItemRepository.save(item);

        // On retourne le panier complet pour voir le nouveau total
        return cartRepository.findById(cartId).orElseThrow();
    }

    // 5. Supprimer un produit du panier
    @DeleteMapping("/{cartId}/items/{itemId}")
    public Cart removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        CartItem item = cartItemRepository.findById(itemId).orElseThrow();

        cart.getItems().remove(item);
        cartItemRepository.delete(item);

        return cartRepository.save(cart);
    }
}