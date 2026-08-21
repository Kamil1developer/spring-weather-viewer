package com.example.weatherviewer.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherSystemInfo(String country) {}
