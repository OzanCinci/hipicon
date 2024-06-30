package com.ozan.be.product.dtos;

import com.ozan.be.product.domain.ProductStatus;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ProductResponseDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private UUID id;
  private String name;
  private Double price;
  private String description;
  private ProductStatus status;
  private Integer quantity;
  private Boolean stock;
  private List<String> imageUrls;
  private String sellerName;
  private String sellerEmail;
}
