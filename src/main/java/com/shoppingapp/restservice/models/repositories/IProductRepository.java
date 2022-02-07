package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
