package com.example.weatherviewer.form;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class LocationForm {
    private final String name;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String country;
    private final String state;
}
