package com.ozan.be.product;

import com.ozan.be.common.Auditable;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends Auditable<UUID> implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;
  private String imageUrl;
  private String category;
  private String description;
  private Double rating = 0.0;
  private Integer numberOfRating = 0;
  private Boolean stock = true;
}
