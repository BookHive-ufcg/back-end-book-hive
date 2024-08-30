package com.bookhive.ufcg.bookhive.exception;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 11L;
	
	public UserNotFoundException(String msg) {
		super(msg);
	}

}
