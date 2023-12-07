package com.hideaway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hideaway.exception.ProductException;
import com.hideaway.model.Cart;
import com.hideaway.model.CartItem;
import com.hideaway.model.Product;
import com.hideaway.model.User;
import com.hideaway.repository.CartRepository;
import com.hideaway.request.AddItemRequest;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private ProductService productService;

	@Override
	public Cart createCart(User user) {

		Cart cart = new Cart();
		cart.setUser(user);
		Cart createdCart = cartRepository.save(cart);
		return createdCart;
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart = cartRepository.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());

		CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);

		if (isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);

			int price = req.getQuantity() * product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());

			CartItem createdCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
		}

		return "Item Add To Cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart = cartRepository.findByUserId(userId);
		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;
		for (CartItem cartsItem : cart.getCartItems()) {
			totalPrice += cartsItem.getPrice();
			totalDiscountedPrice += cartsItem.getDiscountedPrice();
			totalItem += cartsItem.getQuantity();
		}

		cart.setTotalPrice(totalPrice);
		cart.setTotalItem(cart.getCartItems().size());
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setDiscount(totalPrice - totalDiscountedPrice);
		cart.setTotalItem(totalItem);

		return cartRepository.save(cart);
	}

}
