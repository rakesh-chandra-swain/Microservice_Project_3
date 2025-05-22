package com.nt.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
	
	List<Asset> findByUserId(Long userId);
	
	Asset findByUserIdAndCoinId(Long userId,Long coinId);

}
