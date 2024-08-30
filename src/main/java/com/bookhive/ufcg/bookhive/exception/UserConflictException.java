package com.bookhive.ufcg.bookhive.exception;

public class UserConflictException extends Exception {
    private static final long serialVersionUID = 11L;
	
	public UserConflictException(String msg) {
		super(msg);
	}

}
