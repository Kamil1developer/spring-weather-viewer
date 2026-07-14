package com.example.weatherviewer.controller;

import com.example.weatherviewer.form.LoginForm;
import com.example.weatherviewer.service.SessionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignOutController {
    private final SessionService sessionService;
    @GetMapping("/sign-out")
    public String showSignInPage(@CookieValue(name = "SESSION_ID", required = false) String session,
                                 HttpServletResponse response
                                 ){
        sessionService.deleteCurrentSession(session);
        ResponseCookie cookie = ResponseCookie.from("SESSION_ID", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());


        return "redirect:/sign-in";
    }


}
