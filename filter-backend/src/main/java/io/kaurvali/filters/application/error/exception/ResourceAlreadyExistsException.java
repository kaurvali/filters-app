package io.kaurvali.filters.application.error.exception;

import lombok.Getter;

@Getter
public class ResourceAlreadyExistsException extends RuntimeException {
    private final String message;

    public ResourceAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
