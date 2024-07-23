package io.kaurvali.filters.filtering.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Criteria {

    @NotNull
    @JsonProperty("field")
    private FieldType field;

    @NotNull
    @JsonProperty("comparison")
    private ComparisonType comparisonType;

    @NotNull
    @JsonProperty("value")
    private Object value;

}
