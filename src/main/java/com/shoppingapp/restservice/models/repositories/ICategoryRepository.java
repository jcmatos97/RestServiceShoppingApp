package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Category;
import com.shoppingapp.restservice.models.Product;
import com.shoppingapp.restservice.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

public interface ICategoryRepository extends JpaRepository<Category,Integer>{
    @Query(value = "SELECT p.id, p.name, p.image, p.description, p.price, p.stock, p.discount, p.status " +
            "FROM product p JOIN product_category pc ON p.id = pc.id_product JOIN category c ON c.id = pc.id_category" +
            " WHERE pc.id_category = :idPARAM", nativeQuery = true)
    List<ArrayList> getProducts(@Param("idPARAM") int id);

    @Query(value = "SELECT u.id, u.name, u.lastname, u.email, u.password, u.status " +
            "FROM user u " +
            "JOIN category c ON u.id_category = c.id " +
            "WHERE c.id = :idPARAM", nativeQuery = true)
    List<ArrayList> getUsers(@Param("idPARAM") int id);

    @Query(value = "SELECT t.id, t.date_transaction, t.date_shipping, t.status " +
            "FROM transaction t JOIN category c ON t.id_category = c.id " +
            "WHERE c.id = :idPARAM", nativeQuery = true)
    List<ArrayList> getTransactions(@Param("idPARAM") int id);
}
