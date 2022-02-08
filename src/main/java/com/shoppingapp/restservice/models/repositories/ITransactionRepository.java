package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Transaction;
import com.shoppingapp.restservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {
}
