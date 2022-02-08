package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.Transaction;
import com.shoppingapp.restservice.models.User;
import com.shoppingapp.restservice.models.repositories.ITransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TransactionController {

    private ITransactionRepository transactionRepository;

    TransactionController(ITransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transactions")
    List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transactions/{id}")
    Transaction getTransactionById(@PathVariable Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @PostMapping("/transactions")
    Transaction createTransaction(@RequestBody Transaction newTransaction) {
        return transactionRepository.save(newTransaction);
    }

    @PutMapping("/transactions/{id}")
    Transaction updateTransaction(@RequestBody Transaction newTransaction, @PathVariable Integer id) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    transaction.setUser(newTransaction.getUser());
                    transaction.setAddress(newTransaction.getAddress());
                    transaction.setCategory(newTransaction.getCategory());
                    transaction.setDate_transaction(newTransaction.getDate_transaction());
                    transaction.setDate_shipping(newTransaction.getDate_shipping());
                    transaction.setStatus(newTransaction.getStatus());
                    return transactionRepository.save(newTransaction);
                })
                .orElseGet(() -> {
                    newTransaction.setId(id);
                    return transactionRepository.save(newTransaction);
                });
    }

    @DeleteMapping("/transactions/{id}")
    void deleteTransaction(@PathVariable Integer id) {
        transactionRepository.deleteById(id);
    }
}
