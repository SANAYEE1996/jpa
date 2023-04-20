package com.study.jpa.service;

import com.study.jpa.entity.Orders;
import com.study.jpa.entity.Product;
import com.study.jpa.exception.QuantityInsufficientException;
import com.study.jpa.exception.QuantitySoldOutException;
import com.study.jpa.repository.OrdersRepository;
import com.study.jpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductOrderService {

    private static final Logger log = LoggerFactory.getLogger(ProductOrderService.class);

    private final ProductRepository productRepository;

    private final OrdersRepository ordersRepository;

    @Transactional
    public void productOrder(Orders orders){
        try{
            Product product = productRepository.findWithIdForUpdate(orders.getProductId()).orElseThrow(NullPointerException::new);
            product.minusQuantity(orders.getCount());
            productRepository.save(product);
            ordersRepository.save(orders);
        }catch(NullPointerException npe){
            log.error("해당 상품은 존재하지 않습니다.");
        }catch(QuantitySoldOutException e){
            log.error("품절 입니다.");
        }catch(QuantityInsufficientException e){
            log.error("현재 그만큼의 재고가 업습니다 이사람아");
        }
    }

}
