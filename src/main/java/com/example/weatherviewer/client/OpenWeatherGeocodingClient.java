package com.example.weatherviewer.client;

import com.example.weatherviewer.client.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component

public class OpenWeatherGeocodingClient {
    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://api.openweathermap.org/geo/1.0/direct")
            .build();
    private final String apiKey;

    public OpenWeatherGeocodingClient(Environment environment) {
        this.apiKey = environment.getRequiredProperty("OPENWEATHER_API_KEY");
    }

    public List<LocationResponse> searchLocations(String name) {
        return restClient.get()
                .uri("?q={name}&appid={apiKey}", name, apiKey)
                .retrieve()
                .body(new ParameterizedTypeReference<List<LocationResponse>>() {});
    }
}
