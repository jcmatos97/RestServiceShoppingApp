package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.*;
import com.shoppingapp.restservice.models.repositories.IUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private IUserRepository userRepository;

    UserController(IUserRepository userRepository){this.userRepository = userRepository;}

    @GetMapping("/users")
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/users/{id}/addresses")
    List<Address> getUserByIdWithAddresses(@PathVariable Integer id) {
        List<Address> addressList = new ArrayList<>();
        userRepository.getAddresses(id).forEach(obj -> {
            addressList.add(new Address((Integer) obj.get(0), (String) obj.get(1), (String)obj.get(2), (String)obj.get(3), (String)obj.get(4), (String)obj.get(5), (Boolean)obj.get(6)));
        });
        return addressList;
    }

    @PostMapping("/users")
    User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setLastname(newUser.getLastname());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setStatus(newUser.getStatus());
                    return userRepository.save(newUser);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
