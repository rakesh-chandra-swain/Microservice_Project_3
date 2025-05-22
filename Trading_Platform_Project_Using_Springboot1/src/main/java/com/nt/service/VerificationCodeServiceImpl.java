package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.domain.VerificationType;
import com.nt.model.User;
import com.nt.model.VerificationCode;
import com.nt.respository.VerificationCodeRepository;
import com.nt.utils.OtpUtils;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
	
	@Autowired
	private VerificationCodeRepository verificationCodeRepository;

	@Override
	public VerificationCode sendVerificationCode(User user,VerificationType verificationType) {
		VerificationCode verificationCode1=new VerificationCode();
		verificationCode1.setOtp(OtpUtils.generateOTP());
		verificationCode1.setVerificationType(verificationType);
		verificationCode1.setUser(user);
		
		return verificationCodeRepository.save(verificationCode1);
	}

	@Override
	public VerificationCode getVerificationCodeById(Long id) throws Exception {
		Optional<VerificationCode> verificationCode=verificationCodeRepository.findById(id);
		
		if(verificationCode.isPresent()) {
			return verificationCode.get();
		}
		throw new Exception("verification code is not found");
	}

	@Override
	public VerificationCode getVerificationCodeByUser(Long userId) {
		
		return verificationCodeRepository.findByUserId(userId);
	}

	@Override
	public void deleteVerificationCodeById(VerificationCode verificationCode) {
		verificationCodeRepository.delete(verificationCode);
		
	}
	
	
}
