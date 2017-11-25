package com.n26.api.webtransactions.controller;

import com.n26.api.webtransactions.exception.InvalidTimestampException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@ControllerAdvice
public class DefaultController {

    @ResponseStatus(value = NO_CONTENT)
    @ExceptionHandler(InvalidTimestampException.class)
    public void invalidTimestampExceptionHandler() {
        log.error("Invalid Timestamp exception handler");
    }
}
