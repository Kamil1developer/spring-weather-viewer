package com.example.weatherviewer.controller;

import com.example.weatherviewer.client.dto.LocationResponse;
import com.example.weatherviewer.form.DeleteLocationForm;
import com.example.weatherviewer.form.LocationForm;
import com.example.weatherviewer.service.LocationService;
import jakarta.validation.constraints.Pattern;
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
    public String showSearchResults(Model model,
                                    @Pattern(
                                            regexp = "[A-Za-z -]+$",
                                            message = "Используйте латиницу"
                                    )
                                    @RequestParam("location") String location){

        List<LocationResponse> weatherResponses = locationService.search(location);
        model.addAttribute("locations", weatherResponses);
        model.addAttribute("name", location);

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
