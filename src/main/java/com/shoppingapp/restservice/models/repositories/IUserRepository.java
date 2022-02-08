package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Group;
import com.shoppingapp.restservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
