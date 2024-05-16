package com.ozan.be.review.dtos;

import com.ozan.be.product.Product;
import java.util.UUID;
import lombok.Data;

@Data
public class ReviewResponseDTO {
  private UUID id;

  private Product product;

  private String userName; // user full name

  private String email;

  private Integer rating;

  private String comment;

  private Boolean approved;
}
