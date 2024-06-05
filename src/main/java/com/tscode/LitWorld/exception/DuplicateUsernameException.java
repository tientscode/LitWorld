package com.tscode.LitWorld.exception;

public class DuplicateUsernameException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errorMessage;


    public DuplicateUsernameException(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    @Override
    public String getMessage() {
        return errorMessage;

    }

}