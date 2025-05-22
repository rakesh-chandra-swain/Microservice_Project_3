package com.nt.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
	
	Wallet findByUserId(Long userId);

}
