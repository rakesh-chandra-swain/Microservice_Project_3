package com.nt.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
