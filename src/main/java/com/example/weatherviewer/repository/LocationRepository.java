package com.example.weatherviewer.repository;

import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationRepository {
    public Location save(Location location);
    public void deleteByNameAndCoordinates(String name, BigDecimal lat, BigDecimal lan);
    public List<Location> findAllByUserId(User user);
}
