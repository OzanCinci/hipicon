package com.ozan.be.review;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class PendingReviewsService {

    private PendingReviewsRepository pendingReviewsRepository;

    public List<PendingReviews> getAllPendingReviews(){
        return pendingReviewsRepository.findAll();
    }



}
