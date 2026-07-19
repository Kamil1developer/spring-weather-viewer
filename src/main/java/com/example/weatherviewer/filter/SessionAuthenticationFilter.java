package com.example.weatherviewer.filter;

import com.example.weatherviewer.service.SessionService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class SessionAuthenticationFilter extends OncePerRequestFilter {
    private static final List<String> PUBLIC_URLS = List.of("/home");
    private static final String SIGN_IN_PATH = "/sign-in";
    private final SessionService sessionService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        Cookie[] cookies = request.getCookies();

        String sessionId = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSION_ID".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }

        if (PUBLIC_URLS.contains(path) &&
                !path.equals("/sign-in")){

            if (sessionId == null) {
                response.sendRedirect(request.getContextPath() + SIGN_IN_PATH + "?reason=authentication-required");
                filterChain.doFilter(request, response);
            }
            else if (!sessionService.existsBySessionId(sessionId)) {
                response.sendRedirect(request.getContextPath() + SIGN_IN_PATH + "?reason=authentication-required");
                filterChain.doFilter(request, response);
            }
        }

        filterChain.doFilter(request, response);
    }
}
