package com.example.clothingstoreapp.Controllers;

import com.example.clothingstoreapp.Models.ReviewModel;
import com.example.clothingstoreapp.Services.ReviewService;
import com.example.clothingstoreapp.Dto.CreateReviewRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewModel> createReview(@RequestBody CreateReviewRequest request) {
        ReviewModel review = reviewService.createReview(request);
        return ResponseEntity.ok(review);
    }

    @GetMapping
    public ResponseEntity<List<ReviewModel>> getAllReviews() {
        List<ReviewModel> reviews = reviewService.findAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewModel> getReviewById(@PathVariable Integer id) {
        return reviewService.findReviewById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewModel> updateReview(@PathVariable Integer id, @RequestBody CreateReviewRequest request) {
        ReviewModel updatedReview = reviewService.updateReview(id, request);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }
}
