package io.kaurvali.filters.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Filter application backend")
                                .version("1.0")
                                .description("API documentation for Filter backend. Made by Kaur Vali 2024")
                );
    }
}
