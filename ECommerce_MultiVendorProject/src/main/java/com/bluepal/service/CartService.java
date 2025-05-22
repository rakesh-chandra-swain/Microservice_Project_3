package com.bluepal.service;

import com.bluepal.exception.ProductException;
import com.bluepal.model.Cart;
import com.bluepal.model.CartItem;
import com.bluepal.model.Product;
import com.bluepal.model.User;

public interface CartService {
	
	public CartItem addCartItem(User user,
								Product product,
								String size,
								int quantity) throws ProductException;
	
	public Cart findUserCart(User user);

}
