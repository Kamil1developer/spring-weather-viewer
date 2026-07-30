package com.example.weatherviewer.service;

import com.example.weatherviewer.client.LocationResponse;
import com.example.weatherviewer.client.OpenWeatherGeocodingClient;
import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.form.LocationForm;
import com.example.weatherviewer.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final OpenWeatherGeocodingClient openWeatherGeocodingClient;
    private final LocationRepository locationRepository;
    private final SessionService sessionService;

    public List<LocationResponse> search(String name){
        return openWeatherGeocodingClient.searchLocations(name);
    }
    public void addLocation(LocationForm locationForm, UUID sessionId){
        Optional<User> optionalUser = sessionService.getUserId(sessionId);
        User user;
        if (optionalUser.isPresent()){
            user = optionalUser.get();

            Location location = new Location(locationForm.getName(),
                    user,
                    locationForm.getLatitude(),
                    locationForm.getLongitude(),
                    locationForm.getState(),
                    locationForm.getCountry()
            );

            locationRepository.save(location);
        }
    }

}
