package com.example.weatherviewer.service;

import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.client.OpenWeatherGeocodingClient;
import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.form.LocationForm;
import com.example.weatherviewer.repository.LocationRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void addLocation(LocationForm locationForm, UUID sessionId){
        Optional<User> optionalUser = sessionService.getUserId(sessionId);
        User user;
        if (optionalUser.isPresent()){
            user = optionalUser.get();

            Location location = new Location(locationForm.getName(),
                    user,
                    locationForm.getLat(),
                    locationForm.getLon(),
                    locationForm.getState(),
                    locationForm.getCountry()
            );

            locationRepository.save(location);
        }
    }

}
