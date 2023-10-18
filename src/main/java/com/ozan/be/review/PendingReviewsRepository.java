package com.ozan.be.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingReviewsRepository extends JpaRepository<PendingReviews, Integer> {
}
