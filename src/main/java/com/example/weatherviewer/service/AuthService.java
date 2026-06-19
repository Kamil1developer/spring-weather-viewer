package com.example.weatherviewer.service;

import com.example.weatherviewer.auth.AuthResult;
import com.example.weatherviewer.form.LoginForm;
import com.example.weatherviewer.form.RegisterForm;
import com.example.weatherviewer.messages.AuthMessage;
import com.example.weatherviewer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.weatherviewer.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public AuthResult signIn(LoginForm loginForm){
        Optional<User> optionalUser = userRepository.findByLogin(loginForm.getUsername());

        if (optionalUser.isEmpty()){
            return AuthResult.INVALID_LaZOGIN;
        }

        User user = optionalUser.get();


        return isPasswordValid(user, loginForm);
    }

    @Transactional
    public void signUp(RegisterForm registerForm){

    }

    public void confirmPassword(){

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
