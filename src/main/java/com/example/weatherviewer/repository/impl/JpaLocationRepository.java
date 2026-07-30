package com.example.weatherviewer.repository.impl;

import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.repository.LocationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaLocationRepository implements LocationRepository {
    @Override
    public Location save(Location location) {
        return null;
    }

    @Override
    public void deleteByName(String name) {

    }
}
