package com.nt.model;

import com.nt.domain.VerificationType;
import lombok.Data;
@Data
public class TwoFactorAuth {
	
	private boolean isEnabled=false;
	private VerificationType sendTo;

}
