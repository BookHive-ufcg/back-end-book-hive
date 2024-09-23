package com.bookhive.ufcg.bookhive.exception;

import java.io.Serial;

public class BookConflictException extends Exception {
    @Serial
    private static final long serialVersionUID = 11L;

    public BookConflictException(String msg) {
        super(msg);
    }
}
