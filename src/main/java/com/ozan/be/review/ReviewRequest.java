package com.ozan.be.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private Integer productId;
    private Integer rating;
    private String email;
    private String comment;
}
