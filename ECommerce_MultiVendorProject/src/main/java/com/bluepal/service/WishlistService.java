package com.bluepal.service;

import java.util.Optional;

import com.bluepal.exception.WishlistNotFoundException;
import com.bluepal.model.Product;
import com.bluepal.model.User;
import com.bluepal.model.Wishlist;

public interface WishlistService {

    Wishlist createWishlist(User user);

    Wishlist getWishlistByUserId(User user);

    Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException;

}

