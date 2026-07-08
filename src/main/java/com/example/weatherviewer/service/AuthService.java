package com.example.weatherviewer.service;

import com.example.weatherviewer.auth.AuthResult;
import com.example.weatherviewer.entity.Session;
import com.example.weatherviewer.exceptions.LoginAlreadyExistsException;
import com.example.weatherviewer.exceptions.SessionNotFoundException;
import com.example.weatherviewer.form.LoginForm;
import com.example.weatherviewer.form.RegisterForm;
import com.example.weatherviewer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import com.example.weatherviewer.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final SessionService sessionService;

    @Transactional
    public AuthResult signIn(LoginForm loginForm){
        Optional<User> optionalUser = userRepository.findByLogin(loginForm.getUsername());

        if (optionalUser.isEmpty()){
            return AuthResult.INVALID_LOGIN;
        }

        User user = optionalUser.get();
        sessionService.createSessionFor(user);


        return isPasswordValid(user, loginForm);
    }

    @Transactional
    public String getSessionId(String login){
        Optional<User> optionalUser = userRepository.findByLogin(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Session> optionalSession = sessionService.getSessionId(user.getId());
            if (optionalSession.isPresent()){
                Session session = optionalSession.get();

                return session.getId().toString();
            }
        }
        throw new SessionNotFoundException();
    }

    @Transactional
    public AuthResult signUp(RegisterForm registerForm){
        String username = registerForm.getUsername();
        String password = registerForm.getPassword();
        String repeatPassword = registerForm.getRepeatPassword();

        try {
            if (password.equals(repeatPassword)){
                User user = userRepository.save(new User(username,password));
                sessionService.createSessionFor(user);
            }
            else{
                return AuthResult.CONFIRM_PASSWORD_INVALID;
            }
            return AuthResult.CONFIRM_PASSWORD_CORRECT;
        }

        catch (ConstraintViolationException ex){
            throw new LoginAlreadyExistsException();
        }

    }

    public AuthResult isPasswordValid(User user, LoginForm loginForm){
        String enteredPassword = loginForm.getPassword();
        String storedPassword = user.getPassword();

        if (enteredPassword.equals(storedPassword)){
            return AuthResult.CORRECT_PASSWORD;
        };

        return AuthResult.INVALID_PASSWORD;
    }
}
