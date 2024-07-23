package io.kaurvali.filters.application.config;

import io.kaurvali.filters.application.error.ApiErrorLogger;
import io.kaurvali.filters.application.error.ApiErrorMessageFactory;
import io.kaurvali.filters.application.error.ApiErrorTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ErrorHandlerConfig {

    @Bean
    ApiErrorLogger apiErrorLogger() {
        return new ApiErrorLogger();
    }

    @Bean
    ApiErrorMessageFactory apiErrorMessageFactory() {
        return new ApiErrorMessageFactory();
    }

    @Bean
    ApiErrorTranslator apiErrorTranslator() {
        return new ApiErrorTranslator();
    }
}
