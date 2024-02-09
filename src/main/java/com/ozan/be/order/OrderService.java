package com.ozan.be.order;

import com.ozan.be.utils.PageableUtils;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public Page<Order> getAllActiveOrders(Pageable pageable) {
    Pageable finalPageable = PageableUtils.prepareDefaultAuditSorting(pageable);
    Page<Order> ordersPage = orderRepository.findByOrderStatus("active", finalPageable);
    List<Order> orderList = ordersPage.getContent().stream().toList();
    return new PageImpl<>(orderList, ordersPage.getPageable(), ordersPage.getTotalElements());
  }

  public Page<Order> getAllNonActiveOrders(Pageable pageable) {
    Pageable finalPageable = PageableUtils.prepareDefaultAuditSorting(pageable);
    Page<Order> ordersPage = orderRepository.findByOrderStatusNot("active", finalPageable);
    List<Order> orderList = ordersPage.getContent().stream().toList();
    return new PageImpl<>(orderList, ordersPage.getPageable(), ordersPage.getTotalElements());
  }

  public Order saveOrder(Order order) {
    orderRepository.save(order);
    return order;
  }
}
