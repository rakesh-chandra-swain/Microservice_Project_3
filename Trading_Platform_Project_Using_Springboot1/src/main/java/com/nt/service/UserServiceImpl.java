package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.config.JwtProvider;
import com.nt.domain.VerificationType;
import com.nt.model.TwoFactorAuth;
import com.nt.model.User;
import com.nt.respository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
	    String email = jwtProvider.getEmailFromJwtToken(jwt);
	    User user = userRepository.findByEmail(email);

	    if (user == null) {
	        throw new Exception("User not found with email: " + email);
	    }

	    return user;
	}


	@Override
	public User findUserByEmail(String email) throws Exception {
		 User user = userRepository.findByEmail(email);

		    if (user == null) {
		        throw new Exception("User not found with email: " + email);
		    }
		return user;
	}

	@Override
	public User findUserById(Long userId) throws Exception {
	Optional<User> user=userRepository.findById(userId);
	
	if (user.isEmpty()) {
        throw new Exception("User not found");
    }
		return user.get();
	}

	@Override
	public User enableTwoFactorAuthentication(VerificationType verificationType,String sendTo,User user) {
		TwoFactorAuth twoFactorAuth=new TwoFactorAuth();
		twoFactorAuth.setEnabled(true);
		twoFactorAuth.setSendTo(verificationType);
		
		user.setTwoFactorAuth(twoFactorAuth);
		return userRepository.save(user);
	}

	@Override
	public User updatePassword(User user, String newPassword) {
		user.setPassword(newPassword);
		return userRepository.save(user);
	}

}
