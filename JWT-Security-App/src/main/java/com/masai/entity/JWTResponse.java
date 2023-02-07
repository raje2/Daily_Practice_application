package com.masai.entity;

import lombok.Data;

@Data
public class JWTResponse {
	
	private String token;
	
	public JWTResponse(String token){
		super();
		this.token = token;
	}

}
