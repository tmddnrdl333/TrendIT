package com.trendit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "PUT", "PATCH", "DELETE", "OPTIONS", "POST")
                .allowedOrigins("http://localhost:9000", "https://j7a301.p.ssafy.io")
                .maxAge(3000);

    }
}
