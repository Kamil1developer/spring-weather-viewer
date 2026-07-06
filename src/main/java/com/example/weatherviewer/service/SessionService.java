package com.example.weatherviewer.service;

import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.repository.SessionRepository;
import jakarta.transaction.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class SessionService {
    private SessionRepository sessionRepository;
    @Transactional
    public void createSessionFor(User user){
        UUID uuid = UUID.randomUUID();
        Long id = user.getId();
        Instant expiresAt = Instant.now().plus(Duration.ofHours(24));

        sessionRepository.save(new Session(uuid, id, expiresAt));


    }

}
