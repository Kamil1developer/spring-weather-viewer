package com.example.weatherviewer.mapper;

import com.example.weatherviewer.client.dto.LocationRequest;
import com.example.weatherviewer.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public LocationRequest toOpenWeatherRequest(Location location){
        return new LocationRequest(
                location.getLatitude(),
                location.getLongitude()
        );
    }
}
