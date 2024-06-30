package com.ozan.be.product.dtos;

import java.io.Serializable;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequestDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private Double price;
  private String description;
  private Integer quantity;
  private MultipartFile[] images;
}
