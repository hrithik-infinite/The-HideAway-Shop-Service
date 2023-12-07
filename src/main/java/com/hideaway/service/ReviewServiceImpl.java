package com.hideaway.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hideaway.exception.ProductException;
import com.hideaway.model.Product;
import com.hideaway.model.Review;
import com.hideaway.model.User;
import com.hideaway.repository.ProductRepository;
import com.hideaway.repository.ReviewRepository;
import com.hideaway.request.ReviewRequest;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
//		product.getReviews().add(review);
		productRepository.save(product);
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		return reviewRepository.getAllProductsReview(productId);
	}

}
