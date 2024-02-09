package com.ozan.be.corsConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        // enable requests from localhost
        registry
            .addMapping("/**")
            .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS");
      }
    };
  }
}
