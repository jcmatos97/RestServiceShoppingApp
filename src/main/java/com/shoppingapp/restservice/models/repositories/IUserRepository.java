package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Group;
import com.shoppingapp.restservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT a.id, a.street, a.number, a.city, a.postal_code, a.country, a.status FROM address a " +
            "JOIN user u ON u.id = a.id_user " +
            "WHERE u.id = :idPARAM", nativeQuery = true)
    List<ArrayList> getAddresses(@Param("idPARAM") int id);

    @Query(value = "SELECT t.id, t.date_transaction, t.date_shipping, t.status " +
            "FROM transaction t JOIN user u ON t.id_user = u.id " +
            "WHERE u.id = :idPARAM", nativeQuery = true)
    List<ArrayList> getTransactions(@Param("idPARAM") int id);

    User findByUsername(String username);
}
