package com.example.weatherviewer.integration;

import com.example.weatherviewer.config.IntegrationTestConfig;
import com.example.weatherviewer.config.IntegrationTestConfigFlywayConfig;
import com.example.weatherviewer.entity.User;
import com.example.weatherviewer.exceptions.LoginAlreadyExistsException;
import com.example.weatherviewer.repository.UserRepository;
import com.example.weatherviewer.service.AuthService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class,
        IntegrationTestConfigFlywayConfig.class})
public class AuthIntegrationTest {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void shouldNotRegisterUserWithSameLogin(){
        String firstName = "User1";
        String password = "123465";

        User user = userRepository.save(new User(firstName,password));

        String duplicateName = "User1";
        String duplicatePassword = "123465";

        assertThrows(LoginAlreadyExistsException.class, () -> authService.createUser(duplicateName,password));
    }
}
