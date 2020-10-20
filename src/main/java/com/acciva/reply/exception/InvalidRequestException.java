package com.acciva.reply.exception;

import java.util.List;
import java.util.stream.Collectors;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(List<String> errorMessages) {
        super(errorMessages
                .stream()
                .collect(Collectors.joining("\n")));
    }
}
