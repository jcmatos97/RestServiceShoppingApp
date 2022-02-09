package com.shoppingapp.restservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name="group_category")
public class Group {
    public Group(){}

    public Group(Integer id, String name, Boolean status){
        this.id = id;
        this.name = name;
        this.status = status;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Category> categories;

    private Boolean status;
}
