package io.kaurvali.filters.application.error;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class ApiErrorLogger {

    public void logError(ApiErrorCategory category, String message) {
        if (Objects.requireNonNull(category) == ApiErrorCategory.WARNING) {
            log.warn(message);
        } else {
            log.error(message);
        }
    }
}
