package com.example.weatherviewer.repository;

import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.entity.Session;

import java.util.Optional;
import java.util.UUID;

public interface LocationRepository {
    public Optional<Session> findByUserId(Long userId);
    public Location save(Session session);
    public void deleteBySessionId(UUID id);
    public boolean existsById(UUID id);
}
