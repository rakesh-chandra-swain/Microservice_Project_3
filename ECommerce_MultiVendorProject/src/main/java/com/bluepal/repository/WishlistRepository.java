package com.bluepal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUserId(Long userId);
}
