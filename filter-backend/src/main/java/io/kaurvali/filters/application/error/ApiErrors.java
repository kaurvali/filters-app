package io.kaurvali.filters.application.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Accessors(chain = true)
@RequiredArgsConstructor
public enum ApiErrors implements ApiError {
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", ApiErrorCategory.ERROR),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST", ApiErrorCategory.ERROR),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND", ApiErrorCategory.WARNING),
    CONFLICT(HttpStatus.CONFLICT, "CONFLICT", ApiErrorCategory.WARNING);

    private final HttpStatus httpStatus;
    private final String code;
    private final ApiErrorCategory category;
    @Setter
    private String message;
}
