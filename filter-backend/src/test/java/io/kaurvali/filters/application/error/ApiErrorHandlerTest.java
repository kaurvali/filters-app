package io.kaurvali.filters.application.error;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ApiErrorHandlerTest {

    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final ApiErrorLogger apiErrorLogger = new ApiErrorLogger();
    private final ApiErrorMessageFactory apiErrorMessageFactory = new ApiErrorMessageFactory();
    private final ApiErrorTranslator apiErrorTranslator = new ApiErrorTranslator();
    private ApiErrorHandler target;

    @BeforeEach
    void setUp() {
        var requestId = UUID.randomUUID().toString();
        this.request.addHeader("X-Request-Id", requestId);

        this.target = new ApiErrorHandler(apiErrorLogger, apiErrorMessageFactory, apiErrorTranslator);
    }

    @Test
    void releasePaymentErrorHandler_genericException() {
        var res = target.handleException(new Exception("Error"), request);
        assertThat(res.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

        var body = res.getBody();
        assertThat(body, notNullValue());
        assertThat(body.getCode(), containsString("INTERNAL_SERVER_ERROR"));
        assertThat(body.getStatus(), is(500));
        assertThat(body.getMessage(), is("Internal server error"));
    }
}
