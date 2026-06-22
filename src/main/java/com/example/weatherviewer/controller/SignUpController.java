package com.example.weatherviewer.controller;

import com.example.weatherviewer.auth.AuthResult;
import com.example.weatherviewer.form.LoginForm;
import com.example.weatherviewer.form.RegisterForm;
import com.example.weatherviewer.mapper.AuthViewMapper;
import com.example.weatherviewer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final AuthService authService;
    @GetMapping("/sign-up")
    public String showSignInPage(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "sign-up";
    }
    @PostMapping("/sign-up")
    public String signUp(Model model, @ModelAttribute RegisterForm registerForm){
        AuthResult authResult = authService.signUp(registerForm);
        AuthViewMapper mapper = new AuthViewMapper(authResult);
        Optional<String> optionalMessage = mapper.resolveMessage(authResult);

        if (optionalMessage.isPresent()){
            String authMessage = optionalMessage.get();
            model.addAttribute("authMessage", authMessage);
            return "/sign-up";
        }
        return "redirect:/home";
    }
}
