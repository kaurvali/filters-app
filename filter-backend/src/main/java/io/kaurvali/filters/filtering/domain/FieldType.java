package io.kaurvali.filters.filtering.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FieldType {

    @JsonProperty("amount")
    AMOUNT,
    @JsonProperty("title")
    TITLE,
    @JsonProperty("date")
    DATE
}
