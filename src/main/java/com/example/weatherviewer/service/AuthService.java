package com.example.weatherviewer.service;

import com.example.weatherviewer.form.LoginForm;
import com.example.weatherviewer.form.RegisterForm;
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
    public void signIn(LoginForm loginForm){
        Optional<User> optionalUser = userRepository.findByLogin(loginForm.getUsername());
        List<User> userList = userRepository.findAll();
    }

    @Transactional
    public void signUp(RegisterForm registerForm){

    }
}
