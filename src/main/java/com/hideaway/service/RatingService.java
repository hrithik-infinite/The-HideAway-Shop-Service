package com.hideaway.service;

import java.util.List;

import com.hideaway.exception.ProductException;
import com.hideaway.model.Rating;
import com.hideaway.model.User;
import com.hideaway.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
