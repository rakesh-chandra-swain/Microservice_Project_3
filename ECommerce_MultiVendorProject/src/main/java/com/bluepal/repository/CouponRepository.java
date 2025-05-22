package com.bluepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findByCode(String couponCode);
}
