package com.example.weatherviewer.client;

public record LocationResponse(
        String name,
        double latitude,
        double longitude,
        String country,
        String state
) {
}