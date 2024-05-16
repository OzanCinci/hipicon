package com.ozan.be.order;

import com.ozan.be.common.Auditable;
import com.ozan.be.product.Product;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem extends Auditable<UUID> implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(
      name = "product_id",
      nullable = false,
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "order_item_product_fk"))
  private Product product;

  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(
      name = "order_id",
      nullable = false,
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "order_item_order_fk"))
  private Order order;

  private Integer quantity;

  private Double price;

  private String measurements;
}
