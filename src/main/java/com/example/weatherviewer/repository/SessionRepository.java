package com.example.weatherviewer.repository;

import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {
    public Optional<Session> findByUserId(Long userId);
    public Session save(Session session);
}
