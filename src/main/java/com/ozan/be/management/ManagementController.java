package com.ozan.be.management;


import com.ozan.be.order.Order;
import com.ozan.be.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("api/management")
@AllArgsConstructor
@RestController
public class ManagementController {
    private final OrderService orderService;

    @GetMapping("activeOrders")
    public List<Order> getAllActiveOrders(){
        return orderService.getAllActiveOrders();
    }

    @GetMapping("completedOrders")
    public List<Order> getAllNonActiveOrders(){
        return orderService.getAllNonActiveOrders();
    }
}
