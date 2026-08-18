package com.example.weatherviewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientTestConfig {
    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder()
                .baseUrl("https://api.openweathermap.org/");
    }

    @Bean
    public MockRestServiceServer mockServer(
            RestClient.Builder builder
    ) {
        return MockRestServiceServer
                .bindTo(builder)
                .build();
    }

    @Bean
    public RestClient restClient(
            RestClient.Builder builder,
            MockRestServiceServer mockServer
    ) {
        return builder.build();
    }
}
