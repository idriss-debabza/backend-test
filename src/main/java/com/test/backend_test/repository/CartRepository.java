package com.test.backend_test.repository;
import com.test.backend_test.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CartRepository extends JpaRepository<Cart, Long> {}