package com.example.weatherviewer.exceptions;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException() {

        super("Сессия не найдена");
    }
}
