package com.example.weatherviewer.client.dto;

import com.example.weatherviewer.client.Coord;
import com.example.weatherviewer.client.Weather;
import com.example.weatherviewer.client.WeatherMeasurements;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(Coord coord, Weather weather, WeatherMeasurements main) {}
