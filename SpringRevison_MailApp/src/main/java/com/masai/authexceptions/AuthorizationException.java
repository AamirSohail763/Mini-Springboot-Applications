package com.masai.authexceptions;

public class AuthorizationException extends RuntimeException{

	private static final long serialVersionUID = 1L;


	public AuthorizationException() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AuthorizationException(String message) {
		super(message);
	}

}
