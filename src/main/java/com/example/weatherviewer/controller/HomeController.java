package com.example.weatherviewer.controller;

import com.example.weatherviewer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/home")
    public String showHomePage(){
        return "index";
    }
}
