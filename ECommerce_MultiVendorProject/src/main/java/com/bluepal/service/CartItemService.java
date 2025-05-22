package com.bluepal.service;

import com.bluepal.exception.CartItemException;
import com.bluepal.exception.UserException;
import com.bluepal.model.CartItem;

public interface CartItemService {
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
