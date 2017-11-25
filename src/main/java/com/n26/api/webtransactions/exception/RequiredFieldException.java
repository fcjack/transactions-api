package com.n26.api.webtransactions.exception;

public class RequiredFieldException extends IllegalArgumentException {
    public RequiredFieldException(String message) {
        super(message);
    }
}
