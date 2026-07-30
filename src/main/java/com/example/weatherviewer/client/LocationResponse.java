package com.example.weatherviewer.client;


import java.math.BigDecimal;


public record LocationResponse(
        String name,
        BigDecimal latitude,
        BigDecimal longitude,
        String country,
        String state
) {
}