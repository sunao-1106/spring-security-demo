package com.ao.security.exception;

public class PasswordNotMatchException extends RuntimeException {

    public PasswordNotMatchException(String msg) {
        super(msg);
    }

}
