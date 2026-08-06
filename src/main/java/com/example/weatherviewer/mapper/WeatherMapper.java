package com.example.weatherviewer.mapper;

import com.example.weatherviewer.client.dto.OpenWeatherResponse;
import com.example.weatherviewer.dto.WeatherResponse;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {
    public WeatherResponse toResponse(OpenWeatherResponse openWeatherResponse){
        return new WeatherResponse(
                openWeatherResponse.coord(),
                openWeatherResponse.weathers(),
                openWeatherResponse.main()
        );
    }
}
