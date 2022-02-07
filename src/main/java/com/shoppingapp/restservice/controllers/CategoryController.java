package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class CategoryController {
    private ICategoryRepository categoryRepository;

    CategoryController(ICategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    Category getCategoryById(@PathVariable Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @PostMapping("/categories")
    Category createCategory(@RequestBody Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    @PutMapping("/categories/{id}")
    Category updateCategory(@RequestBody Category newCategory, @PathVariable Integer id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    category.setDescription(newCategory.getDescription());
                    category.setStatus(newCategory.getStatus());
                    return categoryRepository.save(newCategory);
                })
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return categoryRepository.save(newCategory);
                });
    }

    @DeleteMapping("/categories/{id}")
    void deleteCategory(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
    }

}
