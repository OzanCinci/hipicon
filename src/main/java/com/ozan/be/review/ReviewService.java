package com.ozan.be.review;

import com.ozan.be.product.Product;
import com.ozan.be.product.ProductService;
import com.ozan.be.user.User;
import com.ozan.be.user.UserService;
import com.ozan.be.utils.PageableUtils;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private final UserService userService;
  private final ProductService productService;

  public Page<Review> getAllApprovedReviews(Pageable pageable) {
    Pageable finalPageable = PageableUtils.prepareDefaultAuditSorting(pageable);
    Page<Review> reviewPage = reviewRepository.findByApproved(true, finalPageable);
    List<Review> reviewList = reviewPage.getContent().stream().toList();
    return new PageImpl<>(reviewList, reviewPage.getPageable(), reviewPage.getTotalElements());
  }

  public Page<Review> getAllPendingReviews(Pageable pageable) {
    Pageable finalPageable = PageableUtils.prepareDefaultAuditSorting(pageable);
    Page<Review> reviewPage = reviewRepository.findByApproved(false, finalPageable);
    List<Review> reviewList = reviewPage.getContent().stream().toList();
    return new PageImpl<>(reviewList, reviewPage.getPageable(), reviewPage.getTotalElements());
  }

  public Integer createReview(ReviewRequest request) {
    User user = userService.getUser(request.getEmail());
    Product product = productService.getProduct(request.getProductId());
    String userName = user.getFirstname() + " " + user.getLastname();

    var review =
        Review.builder()
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

  public Review approveReview(Integer reviewID, Integer productID) {
    // find review and set it as approved
    var review = reviewRepository.findReviewById(reviewID);

    if (!review.isPresent()) return null;

    Review changedReview = review.get();
    changedReview.setApproved(true);
    changedReview.setApprovedAt(LocalDateTime.now());
    Review savedReview = reviewRepository.save(changedReview);

    // update rating of relevant product
    productService.updateProductRating(productID, savedReview);

    return savedReview;
  }

  public ResponseEntity<String> deletePendingReview(Integer reviewID) {
    reviewRepository.deleteById(reviewID);
    return ResponseEntity.ok("Deletion success.");
  }
}
