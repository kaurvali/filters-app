package io.kaurvali.filters.application.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiErrorResponse {
    private final long timestamp = System.currentTimeMillis();
    private int status;
    private String code;
    private String message;

    public static ApiErrorResponse from(ApiError apiError) {
        return new ApiErrorResponse()
                .setStatus(apiError.getHttpStatus().value())
                .setCode(apiError.getCode())
                .setMessage(apiError.getMessage());
    }
}
