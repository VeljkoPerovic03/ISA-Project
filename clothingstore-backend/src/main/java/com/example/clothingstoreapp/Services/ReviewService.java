package com.example.clothingstoreapp.Services;

import com.example.clothingstoreapp.Models.ProductModel;
import com.example.clothingstoreapp.Models.ReviewModel;
import com.example.clothingstoreapp.Models.UserModel;
import com.example.clothingstoreapp.Repositories.ProductRepository;
import com.example.clothingstoreapp.Repositories.ReviewRepository;
import com.example.clothingstoreapp.Repositories.UserRepository;
import com.example.clothingstoreapp.Dto.CreateReviewRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public ReviewModel createReview(CreateReviewRequest request) {
        UserModel user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        ProductModel product = productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        ReviewModel review = new ReviewModel();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        return reviewRepository.save(review);
    }

    public List<ReviewModel> findAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<ReviewModel> findReviewById(Integer id) {
        return reviewRepository.findById(id);
    }

    public ReviewModel updateReview(Integer id, CreateReviewRequest request) {
        ReviewModel review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }
}
