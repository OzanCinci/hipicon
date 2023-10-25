package com.ozan.be.management;


import com.ozan.be.order.Order;
import com.ozan.be.order.OrderService;
import com.ozan.be.review.PendingReviews;
import com.ozan.be.review.PendingReviewsService;
import com.ozan.be.review.Review;
import com.ozan.be.review.ReviewService;
import com.ozan.be.user.User;
import com.ozan.be.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("api/management")
@AllArgsConstructor
@RestController
public class ManagementController {
    private final OrderService orderService;
    private final PendingReviewsService pendingReviewsService;
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
    public List<PendingReviews> getAllPendingReviews(){
        return pendingReviewsService.getAllPendingReviews();
    }

    @GetMapping("reviews")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("allUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("allUsers/{email}")
    public User getUser(@PathVariable("email") String email){
        return userService.getUser(email);
    }

    @GetMapping("landingPage")
    public String getLandingPage(){
        // TODO: decide how to implement landing page data analysis
        return null;
    }
}
