package com.ozan.be.order;

import static jakarta.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ozan.be.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
  @Id
  @SequenceGenerator(
      name = "order_item_sequence",
      sequenceName = "order_item_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = SEQUENCE, generator = "order_item_sequence")
  @Column(name = "id", updatable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(
      name = "product_id",
      nullable = false,
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "order_item_product_fk"))
  private Product product;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
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
