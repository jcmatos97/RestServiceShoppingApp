package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.Category;
import com.shoppingapp.restservice.models.Group;
import com.shoppingapp.restservice.models.repositories.IProductRepository;
import com.shoppingapp.restservice.models.Product;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private IProductRepository productRepository;

    ProductController(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/products/{id}/categories")
    List<Category> getProductByIdWithCategories(@PathVariable Integer id) {
        List<Category> categoryList = new ArrayList<>();
        List<Group> groupList = new ArrayList<>();
        productRepository.getCategories(id).forEach(obj -> {
            Group gr = new Group((Integer)obj.get(4), (String) obj.get(5), (Boolean) obj.get(6));
            categoryList.add(new Category((Integer) obj.get(0), gr, (String) obj.get(1), (String)obj.get(2), (Boolean)obj.get(3)));
        });
        return categoryList;
    }

    @PostMapping("/products")
    Product createProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @PutMapping("/products/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable Integer id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setStock(newProduct.getStock());
                    product.setStatus(newProduct.getStatus());
                    product.setDiscount(newProduct.getDiscount());
                    product.setImage(newProduct.getImage());
                    return productRepository.save(newProduct);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }
}
