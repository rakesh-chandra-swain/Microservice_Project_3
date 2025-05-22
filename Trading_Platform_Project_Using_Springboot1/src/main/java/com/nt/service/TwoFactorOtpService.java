package com.nt.service;

import com.nt.model.TwoFactorOTP;
import com.nt.model.User;

public interface TwoFactorOtpService {
	
	TwoFactorOTP generateOtp(User user, String otp,String jwt);
	TwoFactorOTP findByUser(Long userId);
	TwoFactorOTP findById(String id);
	 boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp);
	 void deleteTwoFactorOTP(TwoFactorOTP twoFactorOTP);
	
}
