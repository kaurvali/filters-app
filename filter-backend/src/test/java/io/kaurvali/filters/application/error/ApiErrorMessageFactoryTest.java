package io.kaurvali.filters.application.error;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class ApiErrorMessageFactoryTest {

    private final ApiErrorMessageFactory target = new ApiErrorMessageFactory();

    @Test
    void build_xRequestIdPassed_xRequestIdInMessage(){
        var requestId = UUID.randomUUID().toString();

        var request = new MockHttpServletRequest();
        request.addHeader("X-Request-Id", requestId);

        var errorMessage = target.build(request, new RuntimeException(), ApiErrorCategory.ERROR);

        assertThat(errorMessage, containsString(requestId));
    }
}
