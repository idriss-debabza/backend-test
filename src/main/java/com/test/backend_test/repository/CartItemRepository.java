package com.test.backend_test.repository;
import com.test.backend_test.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {}