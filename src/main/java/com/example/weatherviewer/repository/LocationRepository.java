package com.example.weatherviewer.repository;

import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.entity.Session;

import java.util.Optional;
import java.util.UUID;

public interface LocationRepository {
    public Location save(Location location);
    public void deleteByName(String name);
}
