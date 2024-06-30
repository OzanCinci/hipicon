package com.ozan.be.product.domain;

import com.ozan.be.common.BaseAuditableEntity;
import com.ozan.be.user.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends BaseAuditableEntity<UUID> implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;
  private Double price;
  private String description;

  @Enumerated(EnumType.STRING)
  private ProductStatus status = ProductStatus.PENDING;

  private Integer quantity;
  private Boolean stock;

  @ElementCollection private List<String> imageUrls;

  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(
      name = "user_id",
      nullable = false,
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "order_user_fk"))
  private User user;
}
