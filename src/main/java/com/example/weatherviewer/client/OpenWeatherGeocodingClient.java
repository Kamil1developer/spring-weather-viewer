package com.example.weatherviewer.client;

import com.example.weatherviewer.client.dto.LocationRequest;
import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.client.dto.OpenWeatherResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;

@Component

public class OpenWeatherGeocodingClient {
    private final RestClient restClient;
    private final String apiKey;

    public OpenWeatherGeocodingClient(RestClient restClient, Environment environment) {
        this.restClient = restClient;
        this.apiKey = environment.getRequiredProperty("OPENWEATHER_API_KEY");
    }

    public List<LocationResponse> searchLocations(String name) {
        return restClient.get()
                .uri("geo/1.0/direct?q={name}&limit={limit}&appid={apiKey}", name, 5, apiKey)
                .retrieve()
                .body(new ParameterizedTypeReference<List<LocationResponse>>() {});
    }

    public OpenWeatherResponse getWeatherByCoordinates(LocationRequest locationRequest){
        BigDecimal lat = locationRequest.lat();
        BigDecimal lon = locationRequest.lon();
        return restClient.get()
                .uri("data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}&units=metric",lat,lon,apiKey)
                .retrieve()
                .body(OpenWeatherResponse.class);
    }
}
