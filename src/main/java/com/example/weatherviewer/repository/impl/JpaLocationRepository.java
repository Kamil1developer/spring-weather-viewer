package com.example.weatherviewer.repository.impl;

import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.repository.LocationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class JpaLocationRepository implements LocationRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Location save(Location location) {
        entityManager.persist(location);
        return location;
    }

    @Override
    public void deleteByName(String name) {
        entityManager.remove();
    }
}
