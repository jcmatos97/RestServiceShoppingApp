package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.DetailTransaction;
import com.shoppingapp.restservice.models.Group;
import com.shoppingapp.restservice.models.Transaction;
import com.shoppingapp.restservice.models.repositories.IDetailTransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DetailTransactionController {

    private IDetailTransactionRepository detailTransactionRepository;

    DetailTransactionController(IDetailTransactionRepository detailTransactionRepository){
        this.detailTransactionRepository = detailTransactionRepository;
    }

    /*
    @GetMapping("/transactions/details")
    List<DetailTransaction> getTransactionDetails() {
        return detailTransactionRepository.findAll();
    }
    */

    @GetMapping("/transactions/details/{id}")
    DetailTransaction getTransactionDetailById(@PathVariable Integer id) {
        return detailTransactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @PostMapping("/transactions/details")
    DetailTransaction createTransactionDetail(@RequestBody DetailTransaction newTransactionDetail) {
        return detailTransactionRepository.save(newTransactionDetail);
    }

    @DeleteMapping("/transactions/details/{id}")
    void deleteTransactionDetail(@PathVariable Integer id) {
        detailTransactionRepository.deleteById(id);
    }
}
