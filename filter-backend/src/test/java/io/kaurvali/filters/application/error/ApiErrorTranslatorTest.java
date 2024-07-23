package io.kaurvali.filters.application.error;

import io.kaurvali.filters.application.error.exception.InvalidBodyException;
import io.kaurvali.filters.application.error.exception.ResourceAlreadyExistsException;
import io.kaurvali.filters.application.error.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.is;

class ApiErrorTranslatorTest {

    private final static String ERROR_MESSAGE = "error message";
    private final ApiErrorTranslator target = new ApiErrorTranslator();
    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        this.request = new MockHttpServletRequest();
        var requestId = UUID.randomUUID().toString();
        this.request.addHeader("X-Request-Id", requestId);
    }

    @Test
    void translateApiError_missingRequestHeader() {
        var res = target.translate(new MissingRequestValueException(ERROR_MESSAGE));

        assertThat(res.getHttpStatus(), is(HttpStatus.BAD_REQUEST));
        assertThat(res.getCode(), is("BAD_REQUEST"));
        assertThat(res.getMessage(), containsStringIgnoringCase(ERROR_MESSAGE));
        assertThat(res.getCategory(), is(ApiErrorCategory.ERROR));
    }

    @Test
    void translateApiError_methodNotSupported() {
        var res = target.translate(new HttpRequestMethodNotSupportedException(ERROR_MESSAGE));

        assertThat(res.getHttpStatus(), is(HttpStatus.BAD_REQUEST));
        assertThat(res.getCode(), is("BAD_REQUEST"));
        assertThat(res.getMessage(), containsStringIgnoringCase(ERROR_MESSAGE));
        assertThat(res.getCategory(), is(ApiErrorCategory.ERROR));
    }

    @Test
    void translateApiError_invalidBody() {
        var res = target.translate(new InvalidBodyException(ERROR_MESSAGE));

        assertThat(res.getHttpStatus(), is(HttpStatus.BAD_REQUEST));
        assertThat(res.getCode(), is("BAD_REQUEST"));
        assertThat(res.getMessage(), containsStringIgnoringCase(ERROR_MESSAGE));
        assertThat(res.getCategory(), is(ApiErrorCategory.ERROR));
    }

    @Test
    void translateApiError_httpMessageNotReadable() {
        var httpInputMessage = new ServletServerHttpRequest(request);
        var res = target.translate(new HttpMessageNotReadableException(ERROR_MESSAGE, httpInputMessage));

        assertThat(res.getHttpStatus(), is(HttpStatus.BAD_REQUEST));
        assertThat(res.getCode(), is("BAD_REQUEST"));
        assertThat(res.getMessage(), containsStringIgnoringCase(ERROR_MESSAGE));
        assertThat(res.getCategory(), is(ApiErrorCategory.ERROR));
    }

    @Test
    void translateApiError_resourceNotFound() {
        var res = target.translate(new ResourceNotFoundException(ERROR_MESSAGE));

        assertThat(res.getHttpStatus(), is(HttpStatus.NOT_FOUND));
        assertThat(res.getCode(), is("NOT_FOUND"));
        assertThat(res.getMessage(), containsStringIgnoringCase(ERROR_MESSAGE));
        assertThat(res.getCategory(), is(ApiErrorCategory.WARNING));
    }

    @Test
    void translateApiError_resourceAlreadyExists() {
        var res = target.translate(new ResourceAlreadyExistsException(ERROR_MESSAGE));

        assertThat(res.getHttpStatus(), is(HttpStatus.CONFLICT));
        assertThat(res.getCode(), is("CONFLICT"));
        assertThat(res.getMessage(), containsStringIgnoringCase(ERROR_MESSAGE));
        assertThat(res.getCategory(), is(ApiErrorCategory.WARNING));
    }

    @Test
    void translateApiError_generic() {
        var res = target.translate(new Exception(ERROR_MESSAGE));

        assertThat(res.getHttpStatus(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(res.getCode(), is("INTERNAL_SERVER_ERROR"));
        assertThat(res.getMessage(), is("Internal server error"));
        assertThat(res.getCategory(), is(ApiErrorCategory.ERROR));
    }
}
