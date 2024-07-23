package io.kaurvali.filters.application.error;

import io.kaurvali.filters.application.error.exception.InvalidBodyException;
import io.kaurvali.filters.application.error.exception.ResourceAlreadyExistsException;
import io.kaurvali.filters.application.error.exception.ResourceNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;

public class ApiErrorTranslator {

    public ApiError translate(Throwable throwable) {
        return switch (throwable) {
            case MissingRequestValueException e -> ApiErrors.BAD_REQUEST.setMessage(e.getMessage());
            case HttpRequestMethodNotSupportedException e -> ApiErrors.BAD_REQUEST.setMessage(e.getMessage());
            case InvalidBodyException e -> ApiErrors.BAD_REQUEST.setMessage(e.getMessage());
            case HttpMessageNotReadableException e -> ApiErrors.BAD_REQUEST.setMessage(e.getMessage());
            case ResourceNotFoundException e -> ApiErrors.NOT_FOUND.setMessage(e.getMessage());
            case ResourceAlreadyExistsException e -> ApiErrors.CONFLICT.setMessage(e.getMessage());
            default -> ApiErrors.INTERNAL_ERROR.setMessage("Internal server error");
        };
    }
}
