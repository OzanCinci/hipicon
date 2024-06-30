package com.ozan.be.product.domain;

import static java.util.Objects.nonNull;

import com.ozan.be.common.AbstractSearchFilter;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchFilter extends AbstractSearchFilter {
  private String name;
  private ProductStatus status;
  private Integer quantity;
  private Boolean stock;
  private UUID sellerID;

  @Override
  public void buildPredicateList(List<Predicate> predicateList) {
    if (nonNull(name)) {
      predicateList.add(com.ozan.be.product.domain.QProduct.product.name.containsIgnoreCase(name));
    }

    if (nonNull(status)) {
      predicateList.add(com.ozan.be.product.domain.QProduct.product.status.eq(status));
    }

    if (nonNull(quantity)) {
      predicateList.add(com.ozan.be.product.domain.QProduct.product.quantity.eq(quantity));
    }

    if (nonNull(stock)) {
      predicateList.add(com.ozan.be.product.domain.QProduct.product.stock.eq(stock));
    }

    if (nonNull(sellerID)) {
      predicateList.add(com.ozan.be.product.domain.QProduct.product.user.id.eq(sellerID));
    }
  }
}
