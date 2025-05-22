package com.bluepal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.domain.AccountStatus;
import com.bluepal.model.Seller;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    Seller findByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus status);
}
