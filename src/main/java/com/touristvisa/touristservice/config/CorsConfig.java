package com.touristvisa.touristservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {



        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**") // Allow all endpoints
                            .allowedOrigins("http://localhost:5173", "http://localhost:3000", "https://frontend.panthiya.edu.lk", "https://panthiya.edu.lk", "https://dea.panthiya.edu.lk")
                            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these HTTP methods
                            .allowedHeaders("*") // Allow all headers
                            .allowCredentials(true); // Allow sending credentials/cookies if needed
                }
            };
        }

}
