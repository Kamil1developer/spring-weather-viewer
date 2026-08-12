package com.example.weatherviewer.client.dto;

import com.example.weatherviewer.client.Coord;
import com.example.weatherviewer.client.Weather;
import com.example.weatherviewer.client.WeatherMeasurements;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherResponse(Coord coord,
                                  @JsonProperty("weather")
                                  List<Weather> weathers,
                                  WeatherMeasurements main) {}
