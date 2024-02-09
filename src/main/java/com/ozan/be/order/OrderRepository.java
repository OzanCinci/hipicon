package com.ozan.be.order;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
  @Query(value = "SELECT * FROM fk_order  WHERE order_status != ?1", nativeQuery = true)
  List<Order> findAllByStatusNot(String status);

  @Query(value = "SELECT * FROM fk_order  WHERE order_status = ?1", nativeQuery = true)
  List<Order> findAllByStatus(String status);

  Page<Order> findByOrderStatusNot(String status, Pageable pageable);

  Page<Order> findByOrderStatus(String status, Pageable pageable);
}
