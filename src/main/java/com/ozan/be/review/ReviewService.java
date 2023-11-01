package com.ozan.be.review;

import com.ozan.be.product.Product;
import com.ozan.be.product.ProductRepository;
import com.ozan.be.product.ProductService;
import com.ozan.be.user.User;
import com.ozan.be.user.UserRepository;
import com.ozan.be.user.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ProductService productService;

    public List<Review> getAllReviews() {
        return  reviewRepository.findAll();
    }

    public List<Review> getApprovedReviews(){
        return reviewRepository.findAllByApproved(true);
    }

    public List<Review> getPendingReviews(){
        return reviewRepository.findAllByApproved(false);
    }

    public Integer createReview(ReviewRequest request){
        User user = userService.getUser(request.getEmail());
        Product product = productService.getProduct(request.getProductId());
        String userName = user.getFirstname() + " " + user.getLastname();

        var review = Review.builder()
                .approved(false)
                .email(user.getEmail())
                .product(product)
                .rating(request.getRating())
                .user(user)
                .approvedAt(null)
                .createdAt(LocalDateTime.now())
                .comment(request.getComment())
                .userName(userName)
                .build();

        Review saveReview = reviewRepository.save(review);
        return saveReview.getId();
    }

    public Review approveReview(Integer reviewID, Integer productID){
        // find review and set it as approved
        var review = reviewRepository.findReviewById(reviewID);

        if (!review.isPresent())
            return null;

        Review changedReview = review.get();
        changedReview.setApproved(true);
        changedReview.setApprovedAt(LocalDateTime.now());
        Review savedReview = reviewRepository.save(changedReview);

        // update rating of relevant product
        productService.updateProductRating(productID,savedReview);

        return savedReview;
    }

    public ResponseEntity<String> deletePendingReview(Integer reviewID){
        reviewRepository.deleteById(reviewID);
        return ResponseEntity.ok("Deletion success.");
    }
}
