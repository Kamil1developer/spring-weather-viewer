package com.example.weatherviewer.integration;

import com.example.weatherviewer.config.IntegrationTestConfig;
import com.example.weatherviewer.config.IntegrationTestConfigFlywayConfig;
import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.exceptions.LoginAlreadyExistsException;
import com.example.weatherviewer.repository.SessionRepository;
import com.example.weatherviewer.repository.UserRepository;
import com.example.weatherviewer.scheduler.SessionCleanupScheduler;
import com.example.weatherviewer.service.AuthService;
import com.example.weatherviewer.service.SessionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class,
        IntegrationTestConfigFlywayConfig.class})
public class SessionIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private  SessionService sessionService;
    @Autowired
    private  SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void shouldCreateSessionAfterSuccessfulRegistration() {
        String username = "User2";
        String password = "123465";

        User user = userRepository.save(new User(username,password));
        Session session = sessionService.createSessionFor(user);

        entityManager.flush();
        entityManager.clear();

        Session sessionCreated = null;
        Optional<Session> optionalSessionCreated = sessionService.getSessionId(user);
        if (optionalSessionCreated.isPresent()){
            sessionCreated = optionalSessionCreated.get();
        }

        assertEquals(session.getId(), sessionCreated.getId());
        assertEquals(session.getExpiresAt(), sessionCreated.getExpiresAt());
        assertEquals(session.getUser().getId(), sessionCreated.getUser().getId());
    }

    @Test
    @Transactional
    void shouldDeleteExpiredSession(){
        String username = "User2";
        String password = "123465";

        User user = userRepository.save(new User(username,password));
        Session session = sessionService.createSessionFor(user);

        entityManager.flush();
        entityManager.clear();

        List<Session> sessionsBeforeDelete = sessionRepository.findAllSessions();

        sessionRepository.deleteByExpiresAtBefore(Instant.now().plus(Duration.ofHours(2)));

        List<Session> sessionsAfterDelete = sessionRepository.findAllSessions();

        assertTrue(sessionsAfterDelete.isEmpty());
    }




}
