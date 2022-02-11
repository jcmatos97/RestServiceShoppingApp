package com.shoppingapp.restservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class PaymentMethod {

    public PaymentMethod(){}

    public PaymentMethod(Integer id, String nameOnCard, String accountNumber, String expiration, String code, String country, Boolean default_card, Boolean status){
        this.id = id;
        this.nameOnCard = nameOnCard;
        this.accountNumber = accountNumber;
        this.expiration = expiration;
        this.code = code;
        this.country = country;
        this.default_card = default_card;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    private String nameOnCard;

    private String accountNumber;

    private String expiration;

    private String code;

    private String country;

    private Boolean default_card;

    private Boolean status;
}
