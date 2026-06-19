package com.example.weatherviewer.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthMessage {
    USER_NOT_FOUND("Пользователь не найден"),
    INVALID_PASSWORD("Пароль неверный"),
    CONFIRM_PASSWORD_INVALID("Пароли не совпадают");

    private final String message;
}
