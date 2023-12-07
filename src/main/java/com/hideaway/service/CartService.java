package com.hideaway.service;

import com.hideaway.exception.ProductException;
import com.hideaway.model.Cart;
import com.hideaway.model.User;
import com.hideaway.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	public Cart findUserCart(Long userId);
}
