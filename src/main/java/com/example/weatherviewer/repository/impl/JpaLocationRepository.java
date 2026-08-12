package com.example.weatherviewer.repository.impl;

import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.repository.LocationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

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
    public void deleteByNameAndCoordinates(String name, BigDecimal lat, BigDecimal lan) {
        entityManager.createQuery("""
                delete from Location location where location.name =: name and
                abs(location.latitude - :lat) < :epsilon and abs(location.longitude - :lon) < :epsilon
                """)
                .setParameter("name", name)
                .setParameter("lat", lat)
                .setParameter("lon", lan)
                .setParameter("epsilon", new BigDecimal("0.005"))
                .executeUpdate();
    }

    @Override
    public List<Location> findAllByUserId(User user) {
        return entityManager.createQuery("select location from Location location where location.user =: user", Location.class)
                .setParameter("user", user)
                .getResultList();
    }
}
