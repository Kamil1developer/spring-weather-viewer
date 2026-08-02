package com.example.weatherviewer.form;

import lombok.*;

import java.math.BigDecimal;

@Data
public class LocationForm {
    private  String name;
    private  BigDecimal lat;
    private  BigDecimal lon;
    private  String country;
    private  String state;
}
