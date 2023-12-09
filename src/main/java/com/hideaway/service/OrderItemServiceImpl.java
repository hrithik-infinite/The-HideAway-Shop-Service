package com.hideaway.service;

import org.springframework.stereotype.Service;

import com.hideaway.model.OrderItem;
import com.hideaway.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemRepository orderItemRepository;
	public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
		this.orderItemRepository=orderItemRepository;
	}
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
