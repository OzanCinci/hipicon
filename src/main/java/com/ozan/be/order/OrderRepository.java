package com.ozan.be.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query(value = "SELECT * FROM fk_order  WHERE order_status != ?1",nativeQuery = true)
    List<Order> findAllByStatusNot(String status);

    @Query(value = "SELECT * FROM fk_order  WHERE order_status = ?1",nativeQuery = true)
    List<Order> findAllByStatus(String status);
}
