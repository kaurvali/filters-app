package io.kaurvali.filters.filtering.function.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;


@Schema(name = "FilterUpdateResponse")
@Data
@Accessors(chain = true)
public class FilterUpdateResponse {

    @JsonProperty("status")
    @Schema(description = "Status of the action")
    private String status;

    @JsonProperty("status_message")
    @Schema(description = "Additional status message for the action")
    private String statusMessage;
}
