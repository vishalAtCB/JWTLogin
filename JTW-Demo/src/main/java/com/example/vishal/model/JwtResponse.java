package com.example.vishal.model;

public class JwtResponse {
	
	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
}
