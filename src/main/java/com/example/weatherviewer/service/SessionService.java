package com.example.weatherviewer.service;

import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.repository.SessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    @Transactional
    public void createSessionFor(User user){
        UUID uuid = UUID.randomUUID();
        Instant expiresAt = Instant.now().plus(Duration.ofHours(1));

        sessionRepository.save(new Session(uuid, user, expiresAt));
    }

    @Transactional
    public Optional<Session> getSessionId(User user){
        Optional<Session> optionalSession = sessionRepository.findByUserId(user);
        if (optionalSession.isPresent()){
            return sessionRepository.findByUserId(user);
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<User> getUserId(UUID sessionId){
        Optional<User> optionalSession = sessionRepository.findBySessionId(sessionId);
        if (optionalSession.isPresent()){
            return sessionRepository.findBySessionId(sessionId);
        }
        return Optional.empty();
    }

    @Transactional
    public void deleteCurrentSession(String session){
        UUID id = UUID.fromString(session);
        sessionRepository.deleteBySessionId(id);
    }

    @Transactional
    public boolean isSessionValid(String sessionId){
        Instant now = Instant.now();
        return sessionRepository.existsByIdAndExpiresAtBefore(UUID.fromString(sessionId), now);
    }
    @Transactional
    public void deleteExpiredSessions(){
        Instant now = Instant.now();
        sessionRepository.deleteByExpiresAtBefore(now);
    }



}
