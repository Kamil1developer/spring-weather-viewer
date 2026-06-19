package com.example.weatherviewer.auth;

import lombok.Getter;

@Getter
public enum AuthResult {
    CORRECT_LOGIN,
    INVALID_LOGIN,
    CORRECT_PASSWORD,
    INVALID_PASSWORD,
    CONFIRM_PASSWORD_INVALID,
    CONFIRM_PASSWORD_CORRECT
}
