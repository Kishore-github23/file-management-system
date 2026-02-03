package com.filemanagement.dto;

import lombok.Data;

@Data
public class AuthResponse {
	private String token;//Contains JWT Tokens
	private String username;
	private String email;
	private String message;
	
	public AuthResponse(String token, String username,String email, String message) {
        this.token = token;
        this.username = username;
        this.email=email;
        this.message = message;
    }

}
