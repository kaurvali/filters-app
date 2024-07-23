package io.kaurvali.filters.filtering.function.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaurvali.filters.filtering.domain.Criteria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Schema(name = "FilterDataResponse")
@Data
@Accessors(chain = true)
public class FilterDataResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("criteria_list")
    private List<Criteria> criteriaList;

}
