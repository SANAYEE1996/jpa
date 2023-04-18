package com.study.jpa.service;

import com.study.jpa.repository.OrdersRepository;
import com.study.jpa.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class ProductOrderServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ProductOrderServiceTest.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    @DisplayName("상품 주문 동시성 테스트")
    public void orderProductTest(){
        log.info("order product 동시성 테스트 시작");

        log.info("order product 동시성 테스트 준비");
        final int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);


    }
}
