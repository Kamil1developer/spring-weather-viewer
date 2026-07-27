package com.example.weatherviewer.service;

import com.example.weatherviewer.client.LocationResponse;
import com.example.weatherviewer.client.OpenWeatherGeocodingClient;
import com.example.weatherviewer.form.LocationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final OpenWeatherGeocodingClient openWeatherGeocodingClient;

    public List<LocationResponse> search(String name){
        return openWeatherGeocodingClient.searchLocations(name);
    }
    public void addLocation(LocationForm locationForm){

    }

}
