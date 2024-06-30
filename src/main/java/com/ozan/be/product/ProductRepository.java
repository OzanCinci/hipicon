package com.ozan.be.product;

import com.ozan.be.product.domain.Product;
import com.ozan.be.product.dtos.DashBoardView;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
    extends JpaRepository<Product, UUID>, QuerydslPredicateExecutor<Product> {
  Optional<Product> findByIdAndUserId(UUID productId, UUID userId);

  @Override
  Page<Product> findAll(Predicate filter, Pageable pageable);

  @Query(
      "SELECT "
          + "COUNT(p) as totalProductCount, "
          + "COALESCE(SUM(p.quantity), 0) as totalStockCount, "
          + "COUNT(CASE WHEN p.quantity = 0 THEN 1 END) as totalOutOfStockCount "
          + "FROM Product p WHERE p.user.id = :userId")
  DashBoardView getDashboardStatistics(@Param("userId") UUID userId);
}
