package com.test.backend_test.controller;

import com.test.backend_test.model.Product;
import com.test.backend_test.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void shouldReturnAllProductsAndStatus200() throws Exception {
        // Arrange
        Product product = new Product(1L, "MacBook Pro", 2000.0, 5, null);
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));

        // Act & Assert
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("MacBook Pro"))
                .andExpect(jsonPath("$[0].price").value(2000.0));
    }
}