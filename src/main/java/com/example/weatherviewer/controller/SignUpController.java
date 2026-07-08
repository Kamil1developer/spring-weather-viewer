package com.example.weatherviewer.controller;

import com.example.weatherviewer.auth.AuthResult;
import com.example.weatherviewer.exceptions.LoginAlreadyExistsException;
import com.example.weatherviewer.form.LoginForm;
import com.example.weatherviewer.form.RegisterForm;
import com.example.weatherviewer.mapper.AuthViewMapper;
import com.example.weatherviewer.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Duration;
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
    public String signUp(@CookieValue("SESSION_ID") String session, Model model,
                         @Validated @ModelAttribute RegisterForm registerForm,
                         BindingResult bindingResult,
                         HttpServletResponse response){
        try {
            if (bindingResult.hasErrors()) {
                return "sign-up-with-errors";
            }

            AuthResult authResult = authService.signUp(registerForm);
            AuthViewMapper mapper = new AuthViewMapper(authResult);
            mapper.applyErrors(authResult, bindingResult);

            if (bindingResult.hasErrors()) {
                return "sign-up-with-errors";
            }

            String login = registerForm.getUsername();
            String sessionId = authService.getSessionId(login);

            ResponseCookie cookie = ResponseCookie.from("SESSION_ID", sessionId)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(Duration.ofDays(1))
                    .sameSite("Lax")
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return "redirect:/home";
        }
        catch (LoginAlreadyExistsException e){
            model.addAttribute("usernameAlreadyExists", true);
            return "sign-up-with-errors";
        }
    }
}
