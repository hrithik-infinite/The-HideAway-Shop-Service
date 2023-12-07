package com.hideaway.service;

import com.hideaway.exception.CartItemException;
import com.hideaway.exception.UserException;
import com.hideaway.model.Cart;
import com.hideaway.model.CartItem;
import com.hideaway.model.Product;

public interface CartItemService {
	public CartItem createCartItem(CartItem cartItem);

	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
