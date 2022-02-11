package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.*;
import com.shoppingapp.restservice.models.repositories.IProductRepository;
import com.shoppingapp.restservice.models.repositories.ITransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionController {

    private ITransactionRepository transactionRepository;
    private IProductRepository productRepository;

    TransactionController(ITransactionRepository transactionRepository, IProductRepository productRepository){
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
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

    @GetMapping("/transactions/{id}/user")
    User getTransactionByIdWithUser(@PathVariable Integer id) {
        Object[] obj = (Object[]) transactionRepository.getUser(id);
        User user = new User((Integer) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], (Boolean) obj[6]);
        return user;
    }

    @GetMapping("/transactions/{id}/address")
    Address getTransactionByIdWithAddress(@PathVariable Integer id) {
        Object[] obj = (Object[]) transactionRepository.getAddress(id);
        Address address = new Address((Integer) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], (Boolean) obj[6]);
        return address;
    }

    @GetMapping("/transactions/{id}/category")
    Category getTransactionByIdWithCategory(@PathVariable Integer id) {
        Object[] obj = (Object[]) transactionRepository.getCategory(id);
        Category category = new Category((Integer) obj[0], (String) obj[1], (String) obj[2], (Boolean) obj[3]);
        return category;
    }

    @GetMapping("/transactions/{id}/details")
    List<DetailTransaction> getTransactionByIdWithDetails(@PathVariable Integer id) {
        List<DetailTransaction> detailTransactionList = new ArrayList<>();
        transactionRepository.getDetails(id).forEach(obj -> {
            Product p = productRepository.findById((Integer)obj.get(1)).orElseThrow(() -> new RuntimeException());;
            detailTransactionList.add(new DetailTransaction((Integer) obj.get(0), p));
        });
        return detailTransactionList;
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
