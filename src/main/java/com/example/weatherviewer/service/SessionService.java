package com.example.weatherviewer.service;

import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    @Transactional
    public Session createSessionFor(User user){
        UUID uuid = UUID.randomUUID();
        Long id = user.getId();
        Instant expiresAt = Instant.now().plus(Duration.ofHours(24));

        return sessionRepository.save(new Session(uuid, id, expiresAt));
    }

    @Transactional
    public Optional<Session> getSessionId(Long userId){
        Optional<Session> optionalSession = sessionRepository.findByUserId(userId);
        if (optionalSession.isPresent()){
            return sessionRepository.findByUserId(userId);
        }
        return Optional.empty();
    }



}
