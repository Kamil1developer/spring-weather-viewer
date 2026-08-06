package com.example.weatherviewer.exceptions.handlers;

import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.exceptions.LocationAlreadyExistsException;
import com.example.weatherviewer.exceptions.LoginAlreadyExistsException;
import com.example.weatherviewer.form.RegisterForm;
import com.example.weatherviewer.service.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.security.auth.login.LoginException;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionsHandler {
    private final LocationService locationService;
    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public String handleLocationAlreadyExists(Model model,
                                              HttpServletRequest request){
        String name = request.getParameter("name");
        List<LocationResponse> weatherResponses = locationService.search(name);
        model.addAttribute("locations", weatherResponses);

        return "search-results";
    }

}
