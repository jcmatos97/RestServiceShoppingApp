package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.Address;
import com.shoppingapp.restservice.models.Category;
import com.shoppingapp.restservice.models.Transaction;
import com.shoppingapp.restservice.models.repositories.IAddressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AddressController {
    private IAddressRepository addressRepository;

    AddressController(IAddressRepository addressRepository){this.addressRepository = addressRepository;}

    @GetMapping("/addresses")
    List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @GetMapping("/addresses/{id}")
    Address getAddressById(@PathVariable Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/addresses/{id}/transactions")
    List<Transaction> getAddressByIdWithTransactions(@PathVariable Integer id) {
        List<Transaction> transactionList = new ArrayList<>();
        addressRepository.getTransactions(id).forEach(obj -> {
            transactionList.add(new Transaction((Integer) obj.get(0), (Date) obj.get(1), (Date)obj.get(2), (Boolean)obj.get(3)));
        });
        return transactionList;
    }

    @PostMapping("/addresses")
    Address createAddress(@RequestBody Address newAddress) {
        return addressRepository.save(newAddress);
    }

    @PutMapping("/addresses/{id}")
    Address updateAddress(@RequestBody Address newAddress, @PathVariable Integer id) {
        return addressRepository.findById(id)
                .map(address -> {
                    address.setStreet(newAddress.getStreet());
                    address.setCity(newAddress.getCity());
                    address.setNumber(newAddress.getNumber());
                    address.setPostalCode(newAddress.getPostalCode());
                    address.setCountry(newAddress.getCountry());
                    address.setStatus(newAddress.getStatus());
                    return addressRepository.save(newAddress);
                })
                .orElseGet(() -> {
                    newAddress.setId(id);
                    return addressRepository.save(newAddress);
                });
    }

    @DeleteMapping("/addresses/{id}")
    void deleteAddress(@PathVariable Integer id) {
        addressRepository.deleteById(id);
    }
}
