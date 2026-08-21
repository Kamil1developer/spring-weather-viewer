package com.example.weatherviewer.filter;

import com.example.weatherviewer.service.SessionService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SessionAuthenticationFilter extends OncePerRequestFilter {

    private static final Set<String> AUTHENTICATED_URLS =
            Set.of("/home", "/search-results", "/add", "/delete");

    private static final String SIGN_IN_PATH = "/sign-in";
    private static final String SESSION_COOKIE_NAME = "SESSION_ID";

    private final SessionService sessionService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        if (!AUTHENTICATED_URLS.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String sessionId = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (SESSION_COOKIE_NAME.equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }

        if (sessionId == null) {
            response.sendRedirect(
                    contextPath + SIGN_IN_PATH + "?reason=authentication-required"
            );
            return;
        }

        if (!sessionService.isSessionValid(sessionId)) {
            response.sendRedirect(
                    contextPath + SIGN_IN_PATH + "?reason=authentication-required"
            );
            return;
        }
        filterChain.doFilter(request, response);
    }
}