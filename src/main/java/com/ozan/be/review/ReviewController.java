package com.ozan.be.review;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/review")
@AllArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("createReview")
    public Integer createReview(@RequestBody ReviewRequest request){
        return reviewService.createReview(request);
    }
}
