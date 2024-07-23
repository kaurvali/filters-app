package io.kaurvali.filters.application.request;


import io.kaurvali.filters.application.error.exception.InvalidBodyException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestBodyValidatorTest {

    private final RequestBodyValidator target = new RequestBodyValidator();

    @Test
    void validate_validBean_noErrors() {
        var validatable = new Validatable()
                .setString("string")
                .setInteger(10);

        target.validate(validatable);
    }

    @Test
    void validate_emptyString_notBlankBreached() {
        var validatable = new Validatable()
                .setString("")
                .setInteger(10);

        var exception = assertThrows(InvalidBodyException.class, () -> target.validate(validatable));

        assertThat(exception, isA(InvalidBodyException.class));
        assertThat(exception.getMessage(), containsStringIgnoringCase("string"));
        assertThat(exception.getMessage(), containsStringIgnoringCase("must not be blank"));
    }

    @Test
    void validate_emptyString_notNullBreached() {
        var validatable = new Validatable()
                .setString("");

        var exception = assertThrows(InvalidBodyException.class, () -> target.validate(validatable));

        assertThat(exception, isA(InvalidBodyException.class));
        assertThat(exception.getMessage(), containsStringIgnoringCase("integer"));
        assertThat(exception.getMessage(), containsStringIgnoringCase("must not be blank"));
    }

    @Test
    void validate_tooBigInteger_maxBreached() {
        var validatable = new Validatable()
                .setString("string")
                .setInteger(35);

        var exception = assertThrows(InvalidBodyException.class, () -> target.validate(validatable));

        assertThat(exception, isA(InvalidBodyException.class));
        assertThat(exception.getMessage(), containsStringIgnoringCase("integer"));
        assertThat(exception.getMessage(), containsStringIgnoringCase("must be less than or equal to 25"));
    }

    @Test
    void validate_twoNullProperties_twoConstraintsBreached() {
        var validatable = new Validatable();

        var exception = assertThrows(InvalidBodyException.class, () -> target.validate(validatable));

        assertThat(exception, isA(InvalidBodyException.class));
        assertThat(exception.getMessage(), containsStringIgnoringCase("string"));
        assertThat(exception.getMessage(), containsStringIgnoringCase("must not be blank"));
        assertThat(exception.getMessage(), containsStringIgnoringCase("integer"));
        assertThat(exception.getMessage(), containsStringIgnoringCase("must not be null"));
    }

    @Data
    @Accessors(chain = true)
    public static class Validatable {

        @NotBlank
        private String string;
        @NotNull
        @Max(25)
        private Integer integer;
    }

}
