package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupRepository extends JpaRepository<Group, Integer>{

}
