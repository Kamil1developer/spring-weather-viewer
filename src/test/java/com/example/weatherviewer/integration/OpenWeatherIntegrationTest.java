package com.example.weatherviewer.integration;

import com.example.weatherviewer.client.OpenWeatherGeocodingClient;
import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.config.IntegrationTestConfig;
import com.example.weatherviewer.config.IntegrationTestConfigFlywayConfig;
import com.example.weatherviewer.config.RestClientConfig;
import com.example.weatherviewer.config.RestClientTestConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        IntegrationTestConfig.class,
        IntegrationTestConfigFlywayConfig.class,
        RestClientTestConfig.class
})
@TestPropertySource(properties = {"OPENWEATHER_API_KEY=1"})
public class OpenWeatherIntegrationTest {
    @Autowired
    private OpenWeatherGeocodingClient restClient;
    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    @Transactional
    void shouldGetSuccessfulResponseFromOpenWeather() throws JsonProcessingException {

        List<LocationResponse> expected = List.of(
                new LocationResponse("Kazan", new BigDecimal(10000), new BigDecimal(20000), "RU", "TR")
        );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(expected);

        mockRestServiceServer.expect(
                requestToUriTemplate("https://api.openweathermap.org/geo/1.0/direct?q={name}&limit={limit}&appid={apiKey}", "Kazan",5, 1)
                )
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withSuccess(
                                json,
                                MediaType.APPLICATION_JSON
                        )
                );

        List<LocationResponse> actualList = restClient.searchLocations("Kazan");

        assertEquals(expected, actualList);

        mockRestServiceServer.verify();
    }
}
