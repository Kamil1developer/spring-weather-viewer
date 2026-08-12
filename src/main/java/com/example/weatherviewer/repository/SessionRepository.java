package com.example.weatherviewer.repository;

import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {
    public Optional<Session> findByUserId(User user);
    public Session save(Session session);
    public void deleteBySessionId(UUID id);
    public boolean existsByIdAndExpiresAtBefore(UUID id, Instant now);
    public Optional<User>  findBySessionId(UUID id);
    public void deleteByExpiresAtBefore(Instant now);
}
