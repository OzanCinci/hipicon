package com.ozan.be.order.dtos;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
public class OrderItemRequestDTO implements Serializable {
  @NotNull private UUID productId;
  @NotNull private Integer quantity;
  @NotNull private Double price;
  @NotNull private String measurements;
}
