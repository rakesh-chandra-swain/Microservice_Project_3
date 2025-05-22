package com.bluepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.OrderItem;



public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
