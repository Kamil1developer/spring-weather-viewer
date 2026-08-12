package com.example.weatherviewer.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public record WeatherMeasurements(
        @JsonProperty("temp")
        BigDecimal temperature,
        @JsonProperty("feels_like")
        BigDecimal feelsLike,
        int humidity
) {}
