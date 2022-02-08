package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.*;
import com.shoppingapp.restservice.models.repositories.ICategoryRepository;
import com.shoppingapp.restservice.models.repositories.IProductRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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

    @GetMapping("/categories/{id}/products")
    List<Product> getCategoryByIdWithProducts(@PathVariable Integer id) {
        List<Product> productList = new ArrayList<>();
        categoryRepository.getProducts(id).forEach(obj -> {
            productList.add(new Product((Integer) obj.get(0), (String) obj.get(1), (String)obj.get(2), (String)obj.get(3), (BigDecimal) obj.get(4), (Integer)obj.get(5), (BigDecimal)obj.get(6), (Boolean)obj.get(7)));
        });
        return productList;
    }

    @GetMapping("/categories/{id}/users")
    List<User> getCategoryByIdWithUsers(@PathVariable Integer id) {
        List<User> userList = new ArrayList<>();
        categoryRepository.getUsers(id).forEach(obj -> {
            userList.add(new User((Integer) obj.get(0), (String) obj.get(1), (String)obj.get(2), (String)obj.get(3), (String)obj.get(4), (Boolean)obj.get(5)));
        });
        return userList;
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
