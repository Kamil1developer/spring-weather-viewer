package com.example.weatherviewer.exceptions;

public class LocationAlreadyExistsException extends RuntimeException {
    public LocationAlreadyExistsException() {

        super("Эта локация уже добавлена");
    }
}
