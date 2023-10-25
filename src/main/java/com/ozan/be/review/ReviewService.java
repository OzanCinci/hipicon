package com.ozan.be.review;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return  reviewRepository.findAll();
    }

}
