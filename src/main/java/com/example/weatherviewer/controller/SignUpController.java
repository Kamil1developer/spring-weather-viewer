package com.example.weatherviewer.controller;

import com.example.weatherviewer.form.LoginForm;
import com.example.weatherviewer.form.RegisterForm;
import com.example.weatherviewer.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    private AuthService authService;
    @GetMapping("/sign-up")
    public String showSignInPage(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "sign-up";
    }
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute RegisterForm registerForm){
        String username = registerForm.getUsername();
        String password = registerForm.getPassword();
        String repeatPassword = registerForm.getRepeatPassword();
        return "redirect:/home";
    }
}
