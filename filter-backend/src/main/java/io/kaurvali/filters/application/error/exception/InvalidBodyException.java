package io.kaurvali.filters.application.error.exception;

import lombok.Getter;

@Getter
public class InvalidBodyException extends RuntimeException {

    private final String message;

    public InvalidBodyException(String message) {
        super(message);
        this.message = message;
    }
}
