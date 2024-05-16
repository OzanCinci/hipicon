package com.ozan.be.review;

import com.ozan.be.common.BaseController;
import com.ozan.be.common.dtos.BasicCreateResponseDTO;
import com.ozan.be.review.dtos.ReviewRequestDTO;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/review")
@AllArgsConstructor
@RestController
public class ReviewController extends BaseController {
  private final ReviewService reviewService;

  @PostMapping("/")
  public ResponseEntity<BasicCreateResponseDTO> createReview(
      @Valid @RequestBody ReviewRequestDTO requestDTO) {
    UUID userId = getCurrentUser().getId();
    UUID id = reviewService.createReview(requestDTO, userId);
    return ResponseEntity.ok(new BasicCreateResponseDTO(id));
  }
}
