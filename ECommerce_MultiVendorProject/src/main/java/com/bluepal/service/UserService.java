package com.bluepal.service;

import java.util.List;

import com.bluepal.exception.UserException;
import com.bluepal.model.User;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;


}
