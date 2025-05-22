package com.nt.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.TwoFactorOTP;
import com.nt.model.User;
import com.nt.respository.TwoFactorOtpRepository;
import com.nt.respository.UserRepository;

@Service
public class TwoFactorOtpServiceImpl implements TwoFactorOtpService {
	
	@Autowired
	private TwoFactorOtpRepository twoFactorOtpRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public TwoFactorOTP generateOtp(User user, String otp, String jwt) {
		UUID uuid=UUID.randomUUID();
		
		String id=uuid.toString();
		
		TwoFactorOTP twoFactorOtp=new TwoFactorOTP();
		twoFactorOtp.setOtp(otp);
		twoFactorOtp.setJwt(jwt);
		twoFactorOtp.setId(id);
		twoFactorOtp.setUser(user);
		
		return twoFactorOtpRepository.save(twoFactorOtp); 
	}

	@Override
	public TwoFactorOTP findByUser(Long userId) {
		
		return twoFactorOtpRepository.findByUserId(userId);
	}

	@Override
	public TwoFactorOTP findById(String id) {
		Optional<TwoFactorOTP> opt=twoFactorOtpRepository.findById(id);
		 return opt.orElse(null);
		             
	}

	@Override
	public boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp) {
		
		return twoFactorOTP.getOtp().equals(otp);
	}

	@Override
	public void deleteTwoFactorOTP(TwoFactorOTP twoFactorOTP) {
		twoFactorOtpRepository.delete(twoFactorOTP);

	}

}
