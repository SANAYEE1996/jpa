package com.study.jpa.repository;

import com.study.jpa.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    void saveProductTest(){

        for(int i = 0; i < 10; i++){
            productRepository.save(new Product((int)(Math.random()*100)));
        }
    }
}
