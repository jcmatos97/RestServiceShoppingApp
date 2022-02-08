package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
