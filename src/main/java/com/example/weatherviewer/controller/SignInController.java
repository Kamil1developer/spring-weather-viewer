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
public class SignInController {
    private final AuthService authService;
    @GetMapping("/sign-in")
    public String showSignInPage(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "sign-in";
    }
    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute LoginForm loginForm, Model model){
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        AuthResult result = authService.signIn(loginForm);
        AuthViewMapper mapper = new AuthViewMapper(result);
        Optional<String> optionalMessage = mapper.resolveMessage(result);

        if (optionalMessage.isPresent()){
            model.addAttribute()
        }
        return "redirect:/home";
    }
}
