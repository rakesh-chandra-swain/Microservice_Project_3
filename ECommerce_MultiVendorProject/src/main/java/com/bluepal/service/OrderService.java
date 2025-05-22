package com.bluepal.service;



import java.util.List;
import java.util.Set;

import com.bluepal.domain.OrderStatus;
import com.bluepal.exception.OrderException;
import com.bluepal.model.Address;
import com.bluepal.model.Cart;
import com.bluepal.model.Order;
import com.bluepal.model.User;

public interface OrderService {
	
	public Set<Order> createOrder(User user, Address shippingAddress, Cart cart);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public List<Order>getShopsOrders(Long sellerId);

	public Order updateOrderStatus(Long orderId,
								   OrderStatus orderStatus)
			throws OrderException;
	
	public void deleteOrder(Long orderId) throws OrderException;

	Order cancelOrder(Long orderId,User user) throws OrderException;
	
}
