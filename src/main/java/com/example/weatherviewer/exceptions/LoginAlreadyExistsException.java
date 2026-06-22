package com.example.weatherviewer.exceptions;

public class LoginAlreadyExistsException extends RuntimeException {
    public LoginAlreadyExistsException() {

        super("Такой логин уже существует");
    }
}
