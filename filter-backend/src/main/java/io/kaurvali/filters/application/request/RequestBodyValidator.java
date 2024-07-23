package io.kaurvali.filters.application.request;

import io.kaurvali.filters.application.error.exception.InvalidBodyException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class RequestBodyValidator {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public void validate(Object body) {
        var violations = validatorFactory.getValidator().validate(body);
        if (!violations.isEmpty()) {
            var validationErrors = violations.stream()
                    .map(violation -> String.format("%s: %s", violation.getPropertyPath(), violation.getMessage()))
                    .collect(Collectors.joining("; "));
            log.warn(validationErrors);
            throw new InvalidBodyException(validationErrors);
        }
    }
}
