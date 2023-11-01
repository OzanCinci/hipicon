package com.ozan.be.management;


import com.ozan.be.order.Order;
import com.ozan.be.order.OrderService;
import com.ozan.be.review.Review;
import com.ozan.be.review.ReviewService;
import com.ozan.be.user.User;
import com.ozan.be.user.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/management")
@AllArgsConstructor
@RestController
public class ManagementController {
    private final OrderService orderService;
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("activeOrders")
    public List<Order> getAllActiveOrders(){
        return orderService.getAllActiveOrders();
    }

    @GetMapping("completedOrders")
    public List<Order> getAllNonActiveOrders(){
        return orderService.getAllNonActiveOrders();
    }

    @GetMapping("pendingReviews")
    public List<Review> getPendingReviews(){
        return reviewService.getPendingReviews();
    }

    @Transactional
    @PutMapping("approveReview/{reviewID}/{productID}")
    public Review approveReview(@PathVariable Integer reviewID, @PathVariable Integer productID){
        return reviewService.approveReview(reviewID, productID);
    }

    @Transactional
    @DeleteMapping("deleteReview/{reviewID}")
    public ResponseEntity<String> deletePendingReview(@PathVariable Integer reviewID){
        return reviewService.deletePendingReview(reviewID);
    }

    @GetMapping("reviews")
    public List<Review> getApprovedReviews(){
        return reviewService.getApprovedReviews();
    }

    @GetMapping("allUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("userSearch")
    public List<User> getUserSearchResult(@RequestParam("q") String searchWord){
        return userService.getUserSearchResult(searchWord);
    }

    @GetMapping("managerSearch")
    public List<User> getManagerUsers(){
        return userService.getManagerUsers();
    }

    @GetMapping("landingPage")
    public String getLandingPage(){
        // TODO: decide how to implement landing page data analysis
        return null;
    }
}
