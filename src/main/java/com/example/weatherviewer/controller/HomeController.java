package com.example.weatherviewer.controller;

import com.example.weatherviewer.service.AuthService;
import com.example.weatherviewer.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final LocationService locationService;
    @GetMapping("/home")
    public String showHomePage(){
        locationService.
        return "index";
    }
}
