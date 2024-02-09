package com.ozan.be.order;

import static jakarta.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ozan.be.user.User;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fk_order")
public class Order implements Serializable {
  @Id
  @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
  @GeneratedValue(strategy = SEQUENCE, generator = "order_sequence")
  @Column(name = "id", updatable = false)
  private Integer id;

  /*
   */
  @JsonBackReference
  @ManyToOne
  @JoinColumn(
      name = "user_id",
      nullable = false,
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "order_user_fk"))
  private User user;

  private String userName;

  private String userEmail;

  private String paymentMethod;

  private Double shippingPrice;

  private Double totalPrice;

  private String address; // TODO: make it longer string

  private String city;

  private Integer postalCode;

  private String country;

  @Column(columnDefinition = "timestamp")
  private LocalDateTime createdAt;

  @Column(columnDefinition = "timestamp")
  private LocalDateTime lastUpdate;

  @Column(name = "order_status")
  private String orderStatus;

  @JsonManagedReference
  @OneToMany(
      mappedBy = "order",
      orphanRemoval = true,
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
      fetch = FetchType.EAGER)
  private List<OrderItem> orderItems = new ArrayList<>();
}
