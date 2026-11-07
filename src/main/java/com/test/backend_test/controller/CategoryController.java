package com.test.backend_test.controller;

import com.test.backend_test.model.Category;
import com.test.backend_test.model.Product;
import com.test.backend_test.repository.CategoryRepository;
import com.test.backend_test.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Category> getAll() { return categoryRepository.findAll(); }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category details) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        category.setName(details.getName());
        category.setDescription(details.getDescription());
        return categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }


    @PostMapping("/{categoryId}/products/{productId}")
    public Category addProductToCategory(@PathVariable Long categoryId, @PathVariable Long productId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        if (!category.getProducts().contains(product)) {
            category.getProducts().add(product);
            categoryRepository.save(category);
        }
        return category;
    }

    @DeleteMapping("/{categoryId}/products/{productId}")
    public Category removeProductFromCategory(@PathVariable Long categoryId, @PathVariable Long productId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        category.getProducts().remove(product);
        return categoryRepository.save(category);
    }
}