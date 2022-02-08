package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface IAddressRepository extends JpaRepository<Address,Integer> {
    @Query(value = "SELECT t.id, t.date_transaction, t.date_shipping, t.status " +
            "FROM transaction t JOIN address a ON t.id_address = a.id " +
            "WHERE a.id = :idPARAM", nativeQuery = true)
    List<ArrayList> getTransactions(@Param("idPARAM") int id);
}
