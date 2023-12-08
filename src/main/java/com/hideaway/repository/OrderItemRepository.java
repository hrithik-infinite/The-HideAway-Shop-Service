package com.hideaway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hideaway.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
