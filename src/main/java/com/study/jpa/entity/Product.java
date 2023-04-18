package com.study.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "product")
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    public void minusQuantity(int quantity){
        this.quantity -= quantity;
    }

    public Product(int quantity) {
        this.quantity = quantity;
    }
}
