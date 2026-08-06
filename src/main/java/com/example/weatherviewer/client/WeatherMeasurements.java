package com.example.weatherviewer.client;

import java.math.BigDecimal;

public record WeatherMeasurements(
        BigDecimal temperature,
        BigDecimal feelsLike,
        int humidity
) {}
