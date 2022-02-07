package com.shoppingapp.restservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
public class ProductCategory {

    public ProductCategory(){}
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    //@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    //@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;
}
