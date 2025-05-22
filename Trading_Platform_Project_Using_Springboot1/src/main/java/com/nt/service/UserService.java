package com.nt.service;

import com.nt.domain.VerificationType;
import com.nt.model.User;

public interface UserService {
	
	public User findUserProfileByJwt(String jwt) throws Exception;
	public User findUserByEmail(String email) throws Exception;
	public User findUserById(Long userId) throws Exception;
	
	public User enableTwoFactorAuthentication(VerificationType verificationType,String sendTo,User user);
	public User updatePassword(User user,String newPassword);
}
