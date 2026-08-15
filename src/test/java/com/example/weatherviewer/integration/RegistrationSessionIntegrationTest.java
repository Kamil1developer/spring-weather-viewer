package com.example.weatherviewer.integration;

import com.example.weatherviewer.config.IntegrationTestConfig;
import com.example.weatherviewer.repository.SessionRepository;
import com.example.weatherviewer.service.SessionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = IntegrationTestConfig.class)
public class RegistrationSessionIntegrationTest {

    @Autowired
    private  SessionService sessionService;
    @Autowired
    private  SessionRepository sessionRepository;

    @Test
    @Transactional
    void shouldCreateSessionAfterSuccessfulRegistration() {

    }
}
