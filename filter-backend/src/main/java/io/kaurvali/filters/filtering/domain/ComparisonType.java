package io.kaurvali.filters.filtering.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum ComparisonType {

    @JsonProperty("equal_to")
    EQUAL_TO(FieldType.AMOUNT),

    @JsonProperty("not_equal_to")
    NOT_EQUAL_TO(FieldType.AMOUNT),

    @JsonProperty("greater_than")
    GREATER_THAN(FieldType.AMOUNT),

    @JsonProperty("less_than")
    LESS_THAN(FieldType.AMOUNT),

    @JsonProperty("greater_than_or_equal_to")
    GREATER_THAN_OR_EQUAL_TO(FieldType.AMOUNT),

    @JsonProperty("less_than_or_equal_to")
    LESS_THAN_OR_EQUAL_TO(FieldType.AMOUNT),

    @JsonProperty("string_equal_to")
    STRING_EQUAL_TO(FieldType.TITLE),

    @JsonProperty("string_not_equal_to")
    STRING_NOT_EQUAL_TO(FieldType.TITLE),

    @JsonProperty("string_contains")
    STRING_CONTAINS(FieldType.TITLE),

    @JsonProperty("string_starts_with")
    STRING_STARTS_WITH(FieldType.TITLE),

    @JsonProperty("string_ends_with")
    STRING_ENDS_WITH(FieldType.TITLE),

    @JsonProperty("string_matches")
    STRING_MATCHES(FieldType.TITLE),

    @JsonProperty("date_equal_to")
    DATE_EQUAL_TO(FieldType.DATE),

    @JsonProperty("date_not_equal_to")
    DATE_NOT_EQUAL_TO(FieldType.DATE),

    @JsonProperty("date_before")
    DATE_BEFORE(FieldType.DATE),

    @JsonProperty("date_after")
    DATE_AFTER(FieldType.DATE),

    @JsonProperty("date_on_or_before")
    DATE_ON_OR_BEFORE(FieldType.DATE),

    @JsonProperty("date_on_or_after")
    DATE_ON_OR_AFTER(FieldType.DATE);

    private final FieldType type;

    ComparisonType(FieldType type) {
        this.type = type;
    }

    public static Set<ComparisonType> getComparisonsByType(FieldType type) {
        return EnumSet.allOf(ComparisonType.class).stream()
                .filter(comparisonType -> comparisonType.getType().equals(type))
                .collect(Collectors.toSet());
    }
}
