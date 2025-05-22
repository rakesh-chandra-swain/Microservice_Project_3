package com.nt.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.domain.VerificationType;
import com.nt.model.ForgotPasswordToken;
import com.nt.model.User;
import com.nt.model.VerificationCode;
import com.nt.request.ForgetPasswordTokenRequest;
import com.nt.request.ResetPasswordRequest;
import com.nt.response.ApiResponse;
import com.nt.response.AuthResponse;
import com.nt.service.EmailService;
import com.nt.service.ForgotPasswordService;
import com.nt.service.UserService;
import com.nt.service.VerificationCodeService;
import com.nt.utils.OtpUtils;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private VerificationCodeService verificationCodeService;
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PatchMapping("/verification/{verificationType}/send-otp")
	public ResponseEntity<String> sendVerificationOtp(@RequestHeader("Authorization") String jwt,
			@PathVariable VerificationType verificationType) throws Exception{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		VerificationCode verificationCode=verificationCodeService.getVerificationCodeByUser(user.getId());
		
		
		if(verificationCode==null) {
			verificationCode=verificationCodeService.sendVerificationCode(user, verificationType);
		}
		if(verificationType.equals(verificationType.EMAIL)) {
			emailService.sendVerificationOtpEmail(user.getEmail(),verificationCode.getOtp());
		}
		
		

	    return new ResponseEntity<>("Verification OTP sent successfully", HttpStatus.OK);
	}
	

	@PatchMapping("/enable-two-factor/verifyOtp/{otp}")
	public ResponseEntity<User> enableTwoFactorAuthentication(
			@RequestHeader("Authorization") String jwt,
			@PathVariable String otp
	) throws Exception{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		VerificationCode verificationCode=verificationCodeService.getVerificationCodeByUser(user.getId());
		
		String sendTo = verificationCode.getVerificationType() == VerificationType.EMAIL ?
                verificationCode.getEmail() : verificationCode.getMobile();
		boolean isVerified=verificationCode.getOtp().equals(otp);
		
		if(isVerified) {
			User updateUser=userService.enableTwoFactorAuthentication(verificationCode.getVerificationType(), sendTo, user);
		
		
		verificationCodeService.deleteVerificationCodeById(verificationCode);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
		}
		
		throw new Exception("wrong otp");
	}
	
	
	@PostMapping("/auth/users/reset-password/send-otp")
	public ResponseEntity<AuthResponse> sendForgotPasswordOtp(
			@RequestBody ForgetPasswordTokenRequest req) throws Exception{
		
		User user=userService.findUserByEmail(req.getSendTo());
		String otp=OtpUtils.generateOTP();
		UUID uuid=UUID.randomUUID();
		String id=uuid.toString();
		
		ForgotPasswordToken token=forgotPasswordService.findByUser(user.getId());
		
		if(token==null) {
			token=forgotPasswordService.createToken(user, id, otp,req.getVerificationType(),req.getSendTo());
		}
		
		if(req.getVerificationType().equals(VerificationType.EMAIL)) {
		
			emailService.sendVerificationOtpEmail(user.getEmail(),token.getOtp());
		}
		AuthResponse response=new AuthResponse();
		response.setSession(token.getId());
		response.setMessage("password reset sent otp successfully");
		

	    return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PatchMapping("/auth/users/reset-password/verify-Otp")
	public ResponseEntity<ApiResponse> resetPassword(
			@RequestHeader("Authorization") String jwt,
			@RequestBody ResetPasswordRequest req,
			@RequestParam String id
	) throws Exception{
		
		
		ForgotPasswordToken forgotPasswordToken=forgotPasswordService.findById(id);
		boolean isVerified=forgotPasswordToken.getOtp().equals(req.getOtp());
		
		   if (isVerified) {
		        userService.updatePassword(forgotPasswordToken.getUser(),req.getPassword());

		        ApiResponse res = new ApiResponse();
		        res.setMessage("Password updated successfully");

		       

		        return new ResponseEntity<>(res, HttpStatus.OK);
		    }
		throw new Exception("wrong otp");
	}
}