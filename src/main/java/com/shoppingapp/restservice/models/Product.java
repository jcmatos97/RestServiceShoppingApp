package com.shoppingapp.restservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
public class Product {
    public Product(){}

    public Product(Integer id, String name, String image, String description, BigDecimal price, Integer stock, BigDecimal discount, Boolean status){
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProductCategory> productCategories;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DetailTransaction> detailTransactions;

    private String name;

    private String image;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private BigDecimal discount;

    private Boolean status;
}
