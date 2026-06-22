package com.example.weatherviewer.exceptions.handlers;

import com.example.weatherviewer.exceptions.LoginAlreadyExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.LoginException;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(LoginAlreadyExistsException.class)
    public String handleLoginAlreadyExists(LoginAlreadyExistsException ex, Model model){
        model.addAttribute("authMessage", ex.getMessage());

        return "/sign-up";

    }
}
