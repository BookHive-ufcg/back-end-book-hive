package com.bookhive.ufcg.bookhive.exception;

import java.io.Serial;

public class BookNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 11L;

    public BookNotFoundException(String msg) {
        super(msg);
    }
}
