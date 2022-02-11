package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.Category;
import com.shoppingapp.restservice.models.PaymentMethod;
import com.shoppingapp.restservice.models.User;
import com.shoppingapp.restservice.models.repositories.IPaymentMethodRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PaymentMethodController {
    private IPaymentMethodRepository paymentMethodRepository;

    PaymentMethodController(IPaymentMethodRepository paymentMethodRepository){
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @GetMapping("/payment/methods")
    List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @GetMapping("/payment/methods/{id}")
    PaymentMethod getPaymentMethodById(@PathVariable Integer id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/payment/methods/{id}/user")
    User getPaymentMethodByIdWithUser(@PathVariable Integer id) {
        Object[] obj = (Object[]) paymentMethodRepository.getUser(id);
        User user = new User((Integer) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], (Boolean) obj[6]);
        return user;
    }

    @PostMapping("/payment/methods")
    PaymentMethod createPaymentMethod(@RequestBody PaymentMethod newPaymentMethod) {
        return paymentMethodRepository.save(newPaymentMethod);
    }

    @PutMapping("/payment/methods/{id}")
    PaymentMethod updatePaymentMethod(@RequestBody PaymentMethod newPaymentMethod, @PathVariable Integer id) {
        return paymentMethodRepository.findById(id)
                .map(paymentMethod -> {
                    paymentMethod.setNameOnCard(newPaymentMethod.getNameOnCard());
                    paymentMethod.setAccountNumber(newPaymentMethod.getAccountNumber());
                    paymentMethod.setExpiration(newPaymentMethod.getExpiration());
                    paymentMethod.setCode(newPaymentMethod.getCode());
                    paymentMethod.setCountry(newPaymentMethod.getCountry());
                    paymentMethod.setDefault_card(newPaymentMethod.getDefault_card());
                    paymentMethod.setStatus(newPaymentMethod.getStatus());
                    return paymentMethodRepository.save(newPaymentMethod);
                })
                .orElseGet(() -> {
                    newPaymentMethod.setId(id);
                    return paymentMethodRepository.save(newPaymentMethod);
                });
    }

    @DeleteMapping("/payment/methods/{id}")
    void deletePaymentMethod(@PathVariable Integer id) {
        paymentMethodRepository.deleteById(id);
    }
}
