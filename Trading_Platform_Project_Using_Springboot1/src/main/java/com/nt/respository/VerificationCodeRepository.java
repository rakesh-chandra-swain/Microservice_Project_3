package com.nt.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.domain.VerificationType;
import com.nt.model.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
	
	public VerificationCode findByUserId(Long userId);
}
