package com.bluepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.PasswordResetToken;



public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
}
