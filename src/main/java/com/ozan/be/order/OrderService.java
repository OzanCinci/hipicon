package com.ozan.be.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;


    public List<Order> getAllActiveOrders(){
        //return orderRepository.findAll();
        return orderRepository.findAllByStatus("active");
    }

    public List<Order> getAllNonActiveOrders(){
        //return orderRepository.findAll();
        return orderRepository.findAllByStatusNot("active");
    }

    public Order saveOrder(Order order){
        orderRepository.save(order);
        return order;
    }
}
