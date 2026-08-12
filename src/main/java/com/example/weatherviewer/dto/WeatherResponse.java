package com.example.weatherviewer.dto;

import com.example.weatherviewer.client.Coord;
import com.example.weatherviewer.client.Weather;
import com.example.weatherviewer.client.WeatherMeasurements;

import java.util.List;

public record WeatherResponse(String name,Coord coord, List<Weather> weathers, WeatherMeasurements main) {
}
