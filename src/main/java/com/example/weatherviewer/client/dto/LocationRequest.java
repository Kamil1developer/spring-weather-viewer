package com.example.weatherviewer.client.dto;

import java.math.BigDecimal;

public record LocationRequest(BigDecimal lat, BigDecimal lon) {}
