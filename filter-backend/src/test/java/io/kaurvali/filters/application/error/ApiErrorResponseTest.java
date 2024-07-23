package io.kaurvali.filters.application.error;

import org.junit.jupiter.api.Test;

import static io.kaurvali.filters.application.error.ApiErrors.INTERNAL_ERROR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

class ApiErrorResponseTest {

    @Test
    void construct_apiError_fieldsPopulated() {
        var apiError = INTERNAL_ERROR;
        var result = ApiErrorResponse.from(apiError);

        assertThat(result.getTimestamp(), greaterThan(0L));
        assertThat(result.getStatus(), is(apiError.getHttpStatus().value()));
        assertThat(result.getCode(), is(apiError.getCode()));
        assertThat(result.getMessage(), is(apiError.getMessage()));
    }
}
