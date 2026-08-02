package com.example.weatherviewer.controller;

import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.form.LocationForm;
import com.example.weatherviewer.service.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationSearchService;
    @GetMapping("/search-results")
    public String showSearchResults(Model model, HttpServletRequest request){
        String name = request.getParameter("location");
        List<LocationResponse> locations = locationSearchService.search(name);
        model.addAttribute("locations", locations);

        return "search-results";
    }

    @PostMapping("/search-results/add")
    public String addLocation(@ModelAttribute LocationForm locationForm,
                              @CookieValue(name = "SESSION_ID", required = false)
                              String sessionId){


        locationSearchService.addLocation(locationForm, UUID.fromString(sessionId));
        return "redirect:/home";
    }
}
