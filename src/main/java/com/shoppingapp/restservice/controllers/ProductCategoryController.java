package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.ProductCategory;
import com.shoppingapp.restservice.models.User;
import com.shoppingapp.restservice.models.repositories.IProductCategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCategoryController {

    private IProductCategoryRepository productCategoryRepository;

    ProductCategoryController(IProductCategoryRepository productCategoryRepository){
        this.productCategoryRepository = productCategoryRepository;
    }

    @GetMapping("/products/categories")
    List<ProductCategory> getProductsCategories() {
        return productCategoryRepository.findAll();
    }

    @GetMapping("/products/categories/{id}")
    ProductCategory getProductCategoryById(@PathVariable Integer id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @PostMapping("/products/categories")
    ProductCategory assignProductsToCategories(@RequestBody ProductCategory newAssignment) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProduct(newAssignment.getProduct());
        productCategory.setCategory(newAssignment.getCategory());
        return productCategoryRepository.save(newAssignment);
    }

    /*
    @PutMapping("/products/categories/{id}")
    ProductCategory updateAssignmentProductsToCategories(@RequestBody ProductCategory newProductCategory, @PathVariable Integer id) {
        return productCategoryRepository.findById(id)
                .map(pc -> {
                    pc.setCategory(newProductCategory.getCategory());
                    pc.setProduct(newProductCategory.getProduct());
                    return productCategoryRepository.save(newProductCategory);
                })
                .orElseGet(() -> {
                    newProductCategory.setId(id);
                    return productCategoryRepository.save(newProductCategory);
                });
    }
     */

    @DeleteMapping("/products/categories/{id}")
    void deleteAssignmentProductsToCategories(@PathVariable Integer id) {
        productCategoryRepository.deleteById(id);
    }
}
