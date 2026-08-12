package com.example.weatherviewer.controller;

import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.form.DeleteLocationForm;
import com.example.weatherviewer.form.LocationForm;
import com.example.weatherviewer.service.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    @GetMapping("/search-results")
    public String showSearchResults(Model model, HttpServletRequest request){
        String name = request.getParameter("location");
        List<LocationResponse> weatherResponses = locationService.search(name);
        model.addAttribute("locations", weatherResponses);
        model.addAttribute("name", name);

        return "search-results";
    }

    @PostMapping("/search-results/add")
    public String addLocation(@ModelAttribute LocationForm locationForm,
                              @CookieValue(name = "SESSION_ID", required = false)
                              String sessionId){


        locationService.addLocation(locationForm, UUID.fromString(sessionId));
        return "redirect:/home";
    }

    @PostMapping("home/delete")
    public String deleteLocation(@ModelAttribute DeleteLocationForm deleteLocationForm){

        locationService.deleteLocation(
                deleteLocationForm.name(),
                deleteLocationForm.lat(),
                deleteLocationForm.lon()
        );
        return "redirect:/home";
    }

}
