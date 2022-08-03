package com.ao.security.exception;

public class UserNotExistException extends RuntimeException{

    public UserNotExistException(String msg) {
        super(msg);
    }

}
