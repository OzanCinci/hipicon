package com.ozan.be.management;

import com.ozan.be.order.Order;
import com.ozan.be.order.OrderService;
import com.ozan.be.review.Review;
import com.ozan.be.review.ReviewService;
import com.ozan.be.user.User;
import com.ozan.be.user.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/management")
@AllArgsConstructor
@RestController
public class ManagementController {
  private final OrderService orderService;
  private final ReviewService reviewService;
  private final UserService userService;

  @GetMapping("/active-orders")
  public ResponseEntity<Page<Order>> getAllActiveOrders(
      @PageableDefault(size = 5) Pageable pageable) {
    Page<Order> orders = orderService.getAllActiveOrders(pageable);
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @GetMapping("/completed-orders")
  public ResponseEntity<Page<Order>> getAllNonActiveOrders(
      @PageableDefault(size = 5) Pageable pageable) {
    Page<Order> orders = orderService.getAllNonActiveOrders(pageable);
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @GetMapping("/pending-reviews")
  public ResponseEntity<Page<Review>> getAllPendingReviews(
      @PageableDefault(size = 5) Pageable pageable) {
    Page<Review> reviews = reviewService.getAllPendingReviews(pageable);
    return new ResponseEntity<>(reviews, HttpStatus.OK);
  }

  @GetMapping("/approved-reviews")
  public ResponseEntity<Page<Review>> getAllApprovedReviews(
      @PageableDefault(size = 5) Pageable pageable) {
    Page<Review> reviews = reviewService.getAllApprovedReviews(pageable);
    return new ResponseEntity<>(reviews, HttpStatus.OK);
  }

  @Transactional
  @PutMapping("/approveReview/{reviewID}/{productID}")
  public Review approveReview(@PathVariable Integer reviewID, @PathVariable Integer productID) {
    return reviewService.approveReview(reviewID, productID);
  }

  @Transactional
  @DeleteMapping("/deleteReview/{reviewID}")
  public ResponseEntity<String> deletePendingReview(@PathVariable Integer reviewID) {
    return reviewService.deletePendingReview(reviewID);
  }

  @GetMapping("/all-users")
  public ResponseEntity<Page<User>> getAllUsers(@PageableDefault(size = 5) Pageable pageable) {
    Page<User> users = userService.getAllUsers(pageable);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/userSearch")
  public List<User> getUserSearchResult(@RequestParam("q") String searchWord) {
    return userService.getUserSearchResult(searchWord);
  }

  @GetMapping("/managerSearch")
  public List<User> getManagerUsers() {
    return userService.getManagerUsers();
  }

  @GetMapping("/landingPage")
  public String getLandingPage() {
    // TODO: decide how to implement landing page data analysis
    return null;
  }
}
