package com.bluepal.service;

import javax.naming.AuthenticationException;

import com.bluepal.exception.ReviewNotFoundException;
import com.bluepal.model.Product;
import com.bluepal.model.Review;
import com.bluepal.model.User;
import com.bluepal.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequest req,
                        User user,
                        Product product);

    List<Review> getReviewsByProductId(Long productId);

    Review updateReview(Long reviewId,
                        String reviewText,
                        double rating,
                        Long userId) throws ReviewNotFoundException, AuthenticationException;


    void deleteReview(Long reviewId, Long userId) throws ReviewNotFoundException, AuthenticationException;

}
