package com.example.weatherviewer.service;

import com.example.weatherviewer.client.dto.LocationRequest;
import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.client.OpenWeatherGeocodingClient;
import com.example.weatherviewer.client.dto.OpenWeatherResponse;
import com.example.weatherviewer.dto.WeatherResponse;
import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.exceptions.LocationAlreadyExistsException;
import com.example.weatherviewer.form.LocationForm;
import com.example.weatherviewer.mapper.LocationMapper;
import com.example.weatherviewer.mapper.WeatherMapper;
import com.example.weatherviewer.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final OpenWeatherGeocodingClient openWeatherGeocodingClient;
    private final LocationRepository locationRepository;
    private final SessionService sessionService;
    private final LocationMapper locationMapper;
    private final WeatherMapper weatherMapper;

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
            try {

                locationRepository.save(location);
            }
            catch (ConstraintViolationException e){
                throw new LocationAlreadyExistsException();
            }
        }
    }

    @Transactional
    public List<WeatherResponse> getLocationsBySessionId(String sessionId){
        Optional<User> optionalUser = sessionService.getUserId(UUID.fromString(sessionId));
        User user;
        if (optionalUser.isPresent()){
            user = optionalUser.get();

            List<Location> locations = locationRepository.findAllByUserId(user);
            List<WeatherResponse> weatherResponses = new LinkedList<>();
            for (Location location: locations ){
                LocationRequest locationRequest = locationMapper.toOpenWeatherRequest(location);
                String name = location.getName();
                OpenWeatherResponse openWeatherResponse = openWeatherGeocodingClient.getWeatherByCoordinates(locationRequest);
                WeatherResponse weatherResponse = weatherMapper.toResponse(name, openWeatherResponse);

                weatherResponses.add(weatherResponse);
            }
            return weatherResponses;



        }

        return List.of();
    }

    @Transactional
    public void deleteLocation(String name, String lat, String lon){
        locationRepository.deleteByNameAndCoordinates(name, new BigDecimal(lat), new BigDecimal(lon));
    }

}
