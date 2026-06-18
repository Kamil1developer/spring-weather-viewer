package com.example.weatherviewer.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthMessage {
    USER_NOT_FOUND("Пользователь не найден"),
    INVALID_PASSWORD("Пароль неверный");

    private final String message;
}
