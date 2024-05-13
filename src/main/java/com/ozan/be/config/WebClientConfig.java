package com.ozan.be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Bean(name = "kasondaApi")
  public WebClient apiClientOne(WebClient.Builder builder) {
    int megaBytes = 8 * 1024 * 1024; // 8MB

    ExchangeStrategies exchangeStrategies =
        ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(megaBytes))
            .build();

    return builder
        .exchangeStrategies(exchangeStrategies)
        .baseUrl("https://api.configurator.vendeco.com/api/rest/")
        .defaultHeader("Content-Type", "application/json")
        .build();
  }
}
