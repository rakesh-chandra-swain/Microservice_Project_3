package com.nt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nt.domain.USER_ROLE;
import com.nt.domain.VerificationType;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_details")
@Data
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fullName;
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Embedded
	private TwoFactorAuth twoFactorAuth=new TwoFactorAuth();
	 
	private USER_ROLE role=USER_ROLE.ROLE_CUSTOMER;

}
