package com.example.weatherviewer.client;

import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.client.dto.WeatherResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component

public class OpenWeatherGeocodingClient {
    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://api.openweathermap.org/")
            .build();
    private final String apiKey;

    public OpenWeatherGeocodingClient(Environment environment) {
        this.apiKey = environment.getRequiredProperty("OPENWEATHER_API_KEY");
    }

    public List<LocationResponse> searchLocations(String name) {
        return restClient.get()
                .uri("geo/1.0/direct/?q={name}&limit={limit}&appid={apiKey}", name, 100, apiKey)
                .retrieve()
                .body(new ParameterizedTypeReference<List<LocationResponse>>() {});
    }

    public WeatherResponse getWeatherByCoordinates(){
        return restClient.get()
                .uri("/data/2.5/weather?lat=55.79&lon=49.12&appid={apiKey}=&units=metric")
                .retrieve()
                .body(WeatherResponse.class);
    }
}
