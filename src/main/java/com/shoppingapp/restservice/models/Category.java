package com.shoppingapp.restservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
public class Category {
    public Category(){}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    //@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_group", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProductCategory> productCategories;

    private String name;

    private String description;

    private Boolean status;
}
