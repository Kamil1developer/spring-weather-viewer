package com.example.weatherviewer.controller;

import com.example.weatherviewer.form.LoginForm;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignOutController {
    @GetMapping("/sign-out")
    public String showSignInPage(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "sign-in";
    }
}
