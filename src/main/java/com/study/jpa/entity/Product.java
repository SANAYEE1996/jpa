package com.study.jpa.entity;

import com.study.jpa.exception.QuantityException;
import com.study.jpa.exception.QuantityInsufficientException;
import com.study.jpa.exception.QuantitySoldOutException;
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

    public void minusQuantity(int quantity) throws QuantityException {
        if(this.quantity - quantity < 0){
            throw new QuantityInsufficientException("수량 초과 입니다.");
        }
        else if(this.quantity == 0){
            throw new QuantitySoldOutException("품절입니다.");
        }
        this.quantity -= quantity;
    }

    public Product(int quantity) {
        this.quantity = quantity;
    }
}
