package io.kaurvali.filters.filtering.function.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaurvali.filters.filtering.domain.Criteria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(name = "FilterUpdateRequest")
@Data
@Accessors(chain = true)
public class FilterUpdateRequest {

    @NotNull
    @NotEmpty
    @JsonProperty("name")
    private String name;

    @Valid
    @JsonProperty("criteria_list")
    private List<Criteria> criteriaList;
}
