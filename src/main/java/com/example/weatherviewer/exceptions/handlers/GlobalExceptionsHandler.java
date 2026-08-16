package com.example.weatherviewer.exceptions.handlers;

import com.example.weatherviewer.client.dto.LocationResponse;

import com.example.weatherviewer.exceptions.LocationAlreadyExistsException;
import com.example.weatherviewer.service.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import javax.security.auth.login.LoginException;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionsHandler {
    private final LocationService locationService;
    @ExceptionHandler(LocationAlreadyExistsException.class)
    public String handleLocationAlreadyExists(Model model,
                                              HttpServletRequest request){
        String name = request.getParameter("name");
        List<LocationResponse> weatherResponses = locationService.search(name);
        model.addAttribute("locations", weatherResponses);
        model.addAttribute("locationAlreadyExists", true);
        return "search-results";
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public String handleMethodValidation(Model model){

        model.addAttribute("validationError", true);
        return "search-results";
    }

}
