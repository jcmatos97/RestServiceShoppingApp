package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.DetailTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetailTransactionRepository extends JpaRepository<DetailTransaction, Integer> {
}
