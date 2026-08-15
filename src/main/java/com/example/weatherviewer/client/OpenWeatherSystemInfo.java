package com.example.weatherviewer.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherSystemInfo(String country) {}
