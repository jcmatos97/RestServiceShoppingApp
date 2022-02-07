package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT c.id, c.name, c.description, c.status, g.id as id_group, g.name as name_group, g.status as status_group FROM category c " +
            "JOIN product_category pc ON c.id = pc.id_category " +
            "JOIN product p ON p.id = pc.id_product " +
            "JOIN group_category g ON c.id_group = g.id " +
            "WHERE pc.id_product = :idPARAM", nativeQuery = true)
    List<ArrayList> getCategories(@Param("idPARAM") int id);
}
