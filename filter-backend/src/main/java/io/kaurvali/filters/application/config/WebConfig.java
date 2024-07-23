package io.kaurvali.filters.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static io.kaurvali.filters.application.constant.Constant.X_REQUEST_ID;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT")
                .allowedHeaders("Content-Type", "Content-Length", X_REQUEST_ID, "Set-Cookie")
                .allowCredentials(true);
    }
}
