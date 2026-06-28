package com.example.weatherviewer.exceptions.handlers;

import com.example.weatherviewer.exceptions.LoginAlreadyExistsException;
import com.example.weatherviewer.form.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.security.auth.login.LoginException;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(LoginAlreadyExistsException.class)
    public String handleLoginAlreadyExists(LoginAlreadyExistsException ex,
                                           Model model,
                                           @ModelAttribute("registerForm") RegisterForm registerForm){
        registerForm.setPassword(null);
        registerForm.setRepeatPassword(null);
        model.addAttribute("usernameAlreadyExists", true);

        return "sign-up-with-errors";

    }
}
