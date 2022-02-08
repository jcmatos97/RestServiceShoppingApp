package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Transaction;
import com.shoppingapp.restservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT u.id, u.name, u.lastname, u.email, u.password, u.status " +
            "FROM user u JOIN transaction t ON u.id = t.id_user " +
            "WHERE t.id = :idPARAM", nativeQuery = true)
    Object getUser(@Param("idPARAM") int id);

    @Query(value = "SELECT a.id, a.street, a.number, a.city, a.postal_code, a.country, a.status " +
            "FROM address a JOIN transaction t ON a.id = t.id_address " +
            "WHERE t.id = :idPARAM", nativeQuery = true)
    Object getAddress(@Param("idPARAM") int id);

    @Query(value = "SELECT c.id, c.name, c.description, c.status " +
            "FROM category c JOIN transaction t ON c.id = t.id_category " +
            "WHERE t.id = :idPARAM", nativeQuery = true)
    Object getCategory(@Param("idPARAM") int id);
}
