package com.bluepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport,Long> {
    SellerReport findBySellerId(Long sellerId);
}
