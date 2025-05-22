package com.bluepal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.Cart;
import com.bluepal.model.CartItem;
import com.bluepal.model.Product;



public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);


}
