package com.shoppingapp.restservice.models.repositories;

import com.shoppingapp.restservice.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface IGroupRepository extends JpaRepository<Group, Integer>{
    @Query(value = "SELECT c.id, c.name, c.description, c.status FROM category c " +
            "JOIN group_category g ON c.id_group = g.id " +
            "WHERE g.id = :idPARAM", nativeQuery = true)
    List<ArrayList> getCategories(@Param("idPARAM") int id);
}
