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
@Table(name="pending_reviews")
public class PendingReviews {
    @Id
    @SequenceGenerator(
            name = "pending_review_sequence",
            sequenceName = "pending_review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "pending_review_sequence"
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
                    name = "pending_review_product_fk"
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
                    name = "pending_review_user_fk"
            )
    )
    private User user;

    private String userName; // user.getFirstName() + " " + user.getLastName()

    private Integer rating;

    private String comment;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime createdAt;
}
