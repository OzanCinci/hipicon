package com.ozan.be.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ozan.be.product.Product;
import com.ozan.be.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="review")
public class Review {
    @Id
    @SequenceGenerator(
            name = "review_sequence",
            sequenceName = "review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "review_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "review_product_fk"
            )
    )
    private Product product;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "review_user_fk"
            )
    )
    private User user;

    private String userName; // user.getFirstName() + " " + user.getLastName()

    private Integer rating;

    private String comment;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime approvedAt;

}
