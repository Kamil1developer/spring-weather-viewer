package com.example.weatherviewer.exceptions;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException() {

        super("Необходимо сначала пройти аутентификацию");
    }
}
