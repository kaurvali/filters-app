package io.kaurvali.filters.application.error;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static io.kaurvali.filters.application.constant.Constant.X_REQUEST_ID;

public class ApiErrorMessageFactory {

    public String build(HttpServletRequest httpServletRequest, Throwable throwable, ApiErrorCategory apiErrorCategory) {
        var requestId = httpServletRequest.getHeader(X_REQUEST_ID);
        return build(requestId, throwable, apiErrorCategory);
    }

    private String build(String requestId, Throwable throwable, ApiErrorCategory apiErrorCategory) {
        return String.format(
                "%s occurred while processing request with identifier %s -> %s",
                apiErrorCategory.name(),
                requestId,
                ExceptionUtils.getStackTrace(throwable));
    }
}
