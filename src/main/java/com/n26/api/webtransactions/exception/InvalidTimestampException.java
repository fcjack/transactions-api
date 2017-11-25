package com.n26.api.webtransactions.exception;

public class InvalidTimestampException extends IllegalArgumentException {

    public InvalidTimestampException(String message) {
        super(message);
    }

}
