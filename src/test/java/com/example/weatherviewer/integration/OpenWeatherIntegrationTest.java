package com.example.weatherviewer.integration;

import com.example.weatherviewer.client.OpenWeatherGeocodingClient;
import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.config.IntegrationTestConfig;
import com.example.weatherviewer.config.IntegrationTestConfigFlywayConfig;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class,
        IntegrationTestConfigFlywayConfig.class})
public class OpenWeatherIntegrationTest {
    @Autowired
    private OpenWeatherGeocodingClient restClient;
    @Test
    @Transactional
    void shouldGetSuccessfulResponseFromOpenWeather(){
        restClient.searchLocations("kazan");
    }
}
