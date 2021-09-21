package com.analytics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://performanceanalytics-app.herokuapp.com",
                                "http://localhost:4200")
                .allowedMethods("POST", "GET")
                .allowCredentials(true);
    }
}
