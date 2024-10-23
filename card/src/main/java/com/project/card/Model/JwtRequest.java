package com.project.card.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

	private String email;
	private String password;
	private String otp;

}
