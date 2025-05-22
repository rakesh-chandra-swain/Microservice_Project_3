package com.bluepal.service;

import com.bluepal.exception.SellerException;
import com.bluepal.exception.UserException;
import com.bluepal.request.LoginRequest;
import com.bluepal.request.SignupRequest;
import com.bluepal.response.AuthResponse;

import jakarta.mail.MessagingException;

public interface AuthService {

    void sentLoginOtp(String email) throws UserException, MessagingException;
    String createUser(SignupRequest req) throws SellerException;
    AuthResponse signin(LoginRequest req) throws SellerException;

}
