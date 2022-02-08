package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface IAddressRepository extends JpaRepository<Address,Integer> {
}
