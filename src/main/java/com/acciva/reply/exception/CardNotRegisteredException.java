package com.acciva.reply.exception;

public class CardNotRegisteredException extends RuntimeException {

    public CardNotRegisteredException() {
        super("Credit card not registered with any existing users");
    }
}
