package io.kaurvali.filters.application.error;

import org.springframework.http.HttpStatus;

public interface ApiError {
    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();

    ApiErrors setMessage(String message);

    ApiErrorCategory getCategory();
}
