package com.acciva.reply.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler  {

    private static Logger LOG = LoggerFactory.getLogger(RestResponseExceptionHandler.class);

    @ExceptionHandler(value = {InvalidRequestException.class})
    ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        LOG.error("Exception thrown '{}'", ex.getMessage(), ex);
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {CardNotRegisteredException.class})
    ResponseEntity<Object> handleNotFoundRequest(RuntimeException ex, WebRequest request) {
        LOG.error("Exception thrown '{}'", ex.getMessage(), ex);
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {UserAlreadyRegisteredException.class})
    ResponseEntity<Object> handleForbiddenRequest(RuntimeException ex, WebRequest request) {
        LOG.error("Exception thrown '{}'", ex.getMessage(), ex);
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}

