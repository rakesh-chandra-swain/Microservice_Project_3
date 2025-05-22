package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.config.JwtProvider;
import com.nt.model.TwoFactorOTP;
import com.nt.model.User;
import com.nt.response.AuthResponse;
import com.nt.respository.UserRepository;
import com.nt.service.CustomeUserDetailsService;
import com.nt.service.EmailService;
import com.nt.service.TwoFactorOtpService;
import com.nt.utils.OtpUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomeUserDetailsService customUserService;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TwoFactorOtpService twoFactorOtpService;
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception{
		User newUser=new User();
		newUser.setFullName(user.getFullName());
		newUser.setPassword(user.getPassword());
		newUser.setEmail(user.getEmail());
		
		User isEmailExsist=userRepository.findByEmail(user.getEmail());
    	
    	if(isEmailExsist!=null) {
    		throw new Exception("Email is Already Used With Another Account");
    	}
		
		User savedUser=userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	
    	String token=jwtProvider.generateToken(authentication);
    	
    	AuthResponse authResponse=new AuthResponse();
    	authResponse.setJwt(token);
    	authResponse.setMessage("Register Success");
    	authResponse.setStatus(true);
		
		return new ResponseEntity<>(authResponse,HttpStatus.CREATED);
	}
	

	 	@PostMapping("/signin")
	    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {
	 		
	        String username = user.getEmail();
	        String password = user.getPassword();

	        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
	        SecurityContextHolder.getContext().setAuthentication(authentication);


	        String token = jwtProvider.generateToken(authentication);
	        
	        User authUser=userRepository.findByEmail(username);
	        
	        if(user.getTwoFactorAuth().isEnabled()) {
	        	AuthResponse authResponse = new AuthResponse();
	        	authResponse.setMessage("Two Factor Auth is Enabled");
	        	authResponse.setTwoFactorAuthEnabled(false);
	        	String otp=OtpUtils.generateOTP();
	        	
	        	TwoFactorOTP oldTwoFactorOTP=twoFactorOtpService.findByUser(authUser.getId());
	        	
	        	if(oldTwoFactorOTP!=null) {
	        		twoFactorOtpService.deleteTwoFactorOTP(oldTwoFactorOTP);
	        	}
	        	TwoFactorOTP newTwoFactorOTP=twoFactorOtpService.generateOtp(authUser,otp,token);
	        	
	        	emailService.sendVerificationOtpEmail(username,otp);
	        	
	        	
	        	
	        	authResponse.setSession(newTwoFactorOTP.getId());
	        	return new ResponseEntity<>(authResponse,HttpStatus.ACCEPTED);
	        	
	        }

	        AuthResponse authResponse = new AuthResponse();
	        authResponse.setJwt(token);
	        authResponse.setStatus(true);
	        authResponse.setMessage("Login Success");
	        

	        return new ResponseEntity<>(authResponse, HttpStatus.OK);
	    }

	    private Authentication authenticate(String username, String password) {
	        UserDetails userDetails = customUserService.loadUserByUsername(username);

	        if (userDetails == null) {
	            throw new BadCredentialsException("Invalid username or password");
	        }

	        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
	            throw new BadCredentialsException("Invalid username or password");
	        }

	        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	    }
	    @PostMapping("/two-factor/otp/{otp}")
	    public ResponseEntity<AuthResponse> verifySignOtp(
	    		@PathVariable String otp,
	    		@RequestParam String id) throws Exception{ 
	    	
	    	TwoFactorOTP twoFactorOTP=twoFactorOtpService.findById(id);
	    	
	    	if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP,otp)) {
	    		AuthResponse res=new AuthResponse();
	    		res.setMessage("Two Factor Authentication Verified");
	    		res.setTwoFactorAuthEnabled(false);
	    		res.setJwt(twoFactorOTP.getJwt());
	    		return new ResponseEntity<>(res,HttpStatus.OK);
	    		
	    	}
	    	throw new Exception("invalid otp");
	    }

}
