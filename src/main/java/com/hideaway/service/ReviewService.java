package com.hideaway.service;

import java.util.List;

import com.hideaway.exception.ProductException;
import com.hideaway.model.Review;
import com.hideaway.model.User;
import com.hideaway.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
	
}
