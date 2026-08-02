package com.example.weatherviewer.controller;

import com.example.weatherviewer.entity.Location;
import com.example.weatherviewer.service.AuthService;
import com.example.weatherviewer.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final LocationService locationService;
    @GetMapping("/home")
    public String showHomePage(@CookieValue(name = "SESSION_ID", required = false)
                                    String sessionId,
                               Model model){
        List<Location> locations = locationService.getLocationsBySessionId(sessionId);

        model.addAttribute("locations", locations);

        return "index";
    }
}
