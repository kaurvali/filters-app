package io.kaurvali.filters.application.error;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiErrorHandler {

    private final ApiErrorLogger apiErrorLogger;
    private final ApiErrorMessageFactory apiErrorMessageFactory;
    private final ApiErrorTranslator apiErrorTranslator;

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleException(Throwable throwable, HttpServletRequest request) {
        var apiError = apiErrorTranslator.translate(throwable);

        var category = apiError.getCategory();
        var message = apiErrorMessageFactory.build(request, throwable, category);
        apiErrorLogger.logError(category, message);

        return ResponseEntity.status(apiError.getHttpStatus()).body(ApiErrorResponse.from(apiError));
    }

}
