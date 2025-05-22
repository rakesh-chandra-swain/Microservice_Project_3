package com.nt.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.ForgotPasswordToken;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken, String> {
	
	ForgotPasswordToken findByUserId(Long userId);
}
