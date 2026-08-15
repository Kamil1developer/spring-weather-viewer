package com.example.weatherviewer.mapper;

import com.example.weatherviewer.client.LocationInfo;
import com.example.weatherviewer.client.dto.OpenWeatherResponse;
import com.example.weatherviewer.dto.WeatherResponse;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {
    public WeatherResponse toResponse(String name, OpenWeatherResponse openWeatherResponse){
        String country = openWeatherResponse.systemInfo().country();
        LocationInfo locationInfo = new LocationInfo(name, country);

        return new WeatherResponse(locationInfo,
                openWeatherResponse.coord(),
                openWeatherResponse.weathers(),
                openWeatherResponse.main()
        );
    }
}
