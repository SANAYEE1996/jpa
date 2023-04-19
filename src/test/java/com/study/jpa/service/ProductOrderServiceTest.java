package com.study.jpa.service;

import com.study.jpa.entity.Orders;
import com.study.jpa.repository.OrdersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class ProductOrderServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ProductOrderServiceTest.class);

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductOrderService productOrderService;

    @Test
    @DisplayName("상품 주문 동시성 테스트")
    public void orderProductTest() throws InterruptedException {
        log.info("order product 동시성 테스트 시작");

        log.info("order product 동시성 테스트 준비");
        final int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        log.info("id가 3인 상품을 주문할 것이며 해당 상품은 총 잔여수량이 1개임");
        Orders oneOrder = new Orders(3L, 1L,1);
        Orders twoOrder = new Orders(3L, 2L, 1);


        log.info("동시성 테스트 실행");
        service.execute(() -> {
            productOrderService.productOrder(oneOrder);
            latch.countDown();
        });
        service.execute(() -> {
            productOrderService.productOrder(twoOrder);
            latch.countDown();
        });

        latch.await();

        log.info("makeReservation 동시성 테스트 결과 검증");
        List<Orders> ordersList = ordersRepository.findAll();
        Assertions.assertThat(ordersList.size()).isEqualTo(1);

    }
}
