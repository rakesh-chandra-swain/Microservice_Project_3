package com.nt.request;

import com.nt.domain.VerificationType;

import lombok.Data;

@Data
public class ForgetPasswordTokenRequest {
	
	private String sendTo;
	private VerificationType verificationType;;

}
