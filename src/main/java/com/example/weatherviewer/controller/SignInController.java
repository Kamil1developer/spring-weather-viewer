package com.example.weatherviewer.controller;

import com.example.weatherviewer.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SignInController {


    @GetMapping("/sign-in")
    public String showSignInPage(){
        return "SIGN IN CONTROLLER WORKS";
    }
}
