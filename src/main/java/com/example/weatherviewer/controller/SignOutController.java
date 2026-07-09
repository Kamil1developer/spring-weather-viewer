package com.example.weatherviewer.controller;

import com.example.weatherviewer.form.LoginForm;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignOutController {
    @GetMapping("/sign-out")
    public String showSignInPage(@CookieValue(name = "SESSION_ID", required = false) String session,
                                 HttpServletResponse response
                                 ){
        ResponseCookie cookie = ResponseCookie.from("SESSION_ID", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());


        return "sign-in";
    }


}
