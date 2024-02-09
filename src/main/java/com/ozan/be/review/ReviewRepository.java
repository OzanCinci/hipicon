package com.ozan.be.review;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

  public List<Review> findAllByApproved(Boolean approved);

  public Optional<Review> findReviewById(Integer reviewID);

  public Page<Review> findByApproved(Boolean approved, Pageable pageable);
}
