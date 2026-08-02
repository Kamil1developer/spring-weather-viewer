package com.example.weatherviewer.client.dto;


import java.math.BigDecimal;


public record LocationResponse(
        String name,
        BigDecimal lat,
        BigDecimal lon,
        String country,
        String state
) {
}