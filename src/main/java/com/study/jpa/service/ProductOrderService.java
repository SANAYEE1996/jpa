package com.study.jpa.service;

import com.study.jpa.entity.Orders;
import com.study.jpa.entity.Product;
import com.study.jpa.repository.OrdersRepository;
import com.study.jpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrderService {

    private final ProductRepository productRepository;

    private final OrdersRepository ordersRepository;

    public void productOrder(Orders orders){
        Product product = productRepository.findById(orders.getProductId()).orElseGet(Product::new);
        if(product.getQuantity() - orders.getCount() >= 0){
            product.minusQuantity(orders.getCount());
            productRepository.save(product);
            ordersRepository.save(orders);
        }
    }

}
