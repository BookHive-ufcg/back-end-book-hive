package com.bookhive.ufcg.bookhive.exception;

public class ReviewNotFoundException extends Exception {
    private static final long serialVersionUID = 11L;
	
	public ReviewNotFoundException(String msg) {
		super(msg);
	}

}