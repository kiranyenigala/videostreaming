package com.acciva.reply.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String userName) {
        super("User already registered " + userName);
    }
}
